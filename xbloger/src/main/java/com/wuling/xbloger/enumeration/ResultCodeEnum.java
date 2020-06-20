package com.wuling.xbloger.enumeration;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc: TODO
 */
public enum ResultCodeEnum {
    OP_FAILURE(-100, "操作失败"),

    QUERY_SUCCESS(200, "查询成功"),
    OP_SUCCESS(201, "操作成功"),

    NO_AUTH(-300, "未登录");

    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
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
