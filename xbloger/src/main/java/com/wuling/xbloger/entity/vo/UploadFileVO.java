package com.wuling.xbloger.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/5/17
 * @Desc: 文本编辑器使用的是smiditor，按照其官方文档说明返回特定的格式
 */
@Getter
@Setter
public class UploadFileVO {

    private boolean success;
    private String file_path;

    public UploadFileVO() {
    }

    public UploadFileVO(boolean success, String file_path) {
        this.success = success;
        this.file_path = file_path;
    }


    public static UploadFileVO buildSuccess(String filePath) {
        return new UploadFileVO(true, filePath);
    }

    public static UploadFileVO buildFailure() {
        return new UploadFileVO(false, "");
    }
}
