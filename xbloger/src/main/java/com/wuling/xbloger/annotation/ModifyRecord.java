package com.wuling.xbloger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: wu_ling
 * @Date: 2020/6/4
 * @Desc: 记录更新操作的表名
 */

@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ModifyRecord {

    /**
     * 表名
     *
     * @return
     */
    String value() default "";

}
