package com.wuling.xbloger.controller.admin;

import com.wuling.xbloger.entity.vo.UploadFileVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    public UploadFileVo upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return UploadFileVo.buildFailure();
        }
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));

        String newFileName = System.currentTimeMillis() + suffix;
        String realPath = imageSavePath + newFileName;
        File dest = new File(realPath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            String imagePath = imageUrl + newFileName;
            return UploadFileVo.buildSuccess(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return UploadFileVo.buildFailure();
    }

}
