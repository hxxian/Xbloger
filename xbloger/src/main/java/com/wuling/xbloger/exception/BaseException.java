package com.wuling.xbloger.exception;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc: TODO
 */
public class BaseException extends RuntimeException {

    protected Integer errCode;
    protected String errMsg;

    public BaseException() {
    }

    public BaseException(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
