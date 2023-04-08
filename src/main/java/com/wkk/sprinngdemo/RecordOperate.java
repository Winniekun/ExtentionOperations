package com.wkk.sprinngdemo;

import java.lang.annotation.*;

/**
 * @author kongweikun@163.com
 * @date 2023/4/7
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordOperate {

    String desc() default "";

    Class<? extends Convert> convert();
}
