package com.wuling.xbloger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: wu_ling
 * @Date: 2020/6/4
 * @Desc: 记录操作更新的列名
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModifyRecordColumn {

    /**
     * 列名
     *
     * @return
     */
    String value() default  "";

    /**
     * 更新描述
     *
     * @return
     */
    String desc() default "";

}
