package com.wuling.xbloger.controller.admin;

import com.wuling.xbloger.entity.vo.UploadFileVO;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @Author: wu_ling
 * @Date: 2020/5/17
 * @Desc:
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Value("${image.savePath}")
    private String imageSavePath;
    @Value("${image.url}")
    private String imageUrl;

    @PostMapping("upload")
    public UploadFileVO upload(@RequestParam("file") MultipartFile file, String fileName) {
        if (file.isEmpty()) {
            return UploadFileVO.buildFailure();
        }
//        String filename = file.getOriginalFilename();
//        String suffix = filename.substring(filename.lastIndexOf("."));
//
//        String newFileName = System.currentTimeMillis() + suffix;
        String newFileName = fileName;
        try {
            newFileName = URLDecoder.decode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String realPath = imageSavePath + newFileName;
        File dest = new File(realPath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);

            if (dest.exists()) {
                Thumbnails.Builder<File> fileBuilder = Thumbnails.of(dest).scale(1.0).outputQuality(1.0);
                BufferedImage src = fileBuilder.asBufferedImage();
                int w = src.getWidth();
                int h = src.getHeight();

                if (w * h <= 1500 * 1800) {
                    dest.delete();
                    return UploadFileVO.buildSuccess("invalid size");
                }

                if (w * h > 1512 * 1830) {
                    Thumbnails.of(dest).width(1512).height(1830).toFile(dest);
                }
            }

            String imagePath = imageUrl + newFileName + "?v" + new Date().getTime();
            return UploadFileVO.buildSuccess(imagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UploadFileVO.buildFailure();
    }

}
