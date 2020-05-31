package com.wuling.xbloger.aop;

import com.wuling.xbloger.enumeration.ErrorCodeEnum;
import com.wuling.xbloger.exception.AuthException;
import com.wuling.xbloger.manager.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc: 管理后台相关接口token校验
 */
//@Aspect
//@Component
@Slf4j
public class AdminTokenVerifyAop {

    @Pointcut("execution(* com.wuling.xbloger.controller.admin.*.*(..)) && !execution(* com.wuling.xbloger.controller.admin.UserController.*(..))")
    public void cutController() {}

    @Before("cutController()")
    public void doVerify(JoinPoint point) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = sra.getRequest();
        String token = request.getHeader("Access-Token");
        boolean isValid = TokenManager.getInstance().checkTokenIsValid(token);
        if (!isValid) {
            throw new AuthException(ErrorCodeEnum.NO_AUTH.getCode(), ErrorCodeEnum.NO_AUTH.getMsg());
        }
    }
}
