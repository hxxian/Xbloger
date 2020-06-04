package com.wuling.xbloger.handler;

import com.wuling.xbloger.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc: 全局异常处理器
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 捕获通用异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Void> commonExceptionHandle(Exception e) {
        log.error("<<Global Common Exception>>, e: [{}]", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 捕获未授权异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<Void> authExceptionHandle(AuthException e) {
        log.error("<<AuthException>>, errCode: [{}], errMsg: [{}]", e.getErrCode(), e.getErrMsg());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

}
