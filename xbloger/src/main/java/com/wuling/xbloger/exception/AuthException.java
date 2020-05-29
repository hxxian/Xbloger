package com.wuling.xbloger.exception;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc: 授权异常
 */
public class AuthException extends BaseException {

    public AuthException(Integer errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
