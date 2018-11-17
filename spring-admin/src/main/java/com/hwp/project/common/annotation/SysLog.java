package com.hwp.project.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author Mark wolaiwod@gmail.com
 * @since 1.3.0 2017-03-08
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
