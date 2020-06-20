package com.wuling.xbloger.controller;

import com.wuling.xbloger.enumeration.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wu_ling
 * @Date: 2020/6/20
 * @Desc: 统一响应结果
 */
@Getter
@Setter
public class R {

    private Integer code;
    private String msg;
    private Object data;

    public R() {
    }

    public R(ResultCodeEnum codeEnum, Object data) {
       this(codeEnum.getCode(), codeEnum.getMsg(), data);
    }

    public R(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
