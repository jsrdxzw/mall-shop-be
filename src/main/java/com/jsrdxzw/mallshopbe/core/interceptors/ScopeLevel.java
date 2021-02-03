package com.jsrdxzw.mallshopbe.core.interceptors;

import java.lang.annotation.*;

/**
 * 用户等级标识
 *
 * @author xuzhiwei
 * @date 2021-02-03
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ScopeLevel {
    int value() default 4;
}
