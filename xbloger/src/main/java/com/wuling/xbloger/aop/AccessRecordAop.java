package com.wuling.xbloger.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: wu_ling
 * @Date: 2020/5/16
 * @Desc: 访问登记
 */
@Aspect
@Component
public class AccessRecordAop {

    @Pointcut("execution(* com.wuling.xbloger.controller.client.*.*(..))")
    public void cutController() {}

    @Before("cutController()")
    public void accessRecord(JoinPoint point) {

    }

}
