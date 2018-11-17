package com.hwp.project.annotation;

import java.lang.annotation.*;

/**
 * 登录效验
 *
 * @author david-hwp
 * @email wolaiwod@gmail.com
 * @date 2017/9/23 14:30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
