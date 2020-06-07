package com.wuling.xbloger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: wu_ling
 * @Date: 2020/6/7
 * @Desc: 管理员操作记录
 */
@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface OperateRecord {

    /**
     * 操作描述
     * @return
     */
    String value() default  "";
}
