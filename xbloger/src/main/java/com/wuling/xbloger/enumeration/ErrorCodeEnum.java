package com.wuling.xbloger.enumeration;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc: TODO
 */
public enum ErrorCodeEnum {
    NO_AUTH(-100, "未登录");

    private Integer code;
    private String msg;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
