package com.jsrdxzw.mallshopbe.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Getter
@AllArgsConstructor
public enum LoginType {
    /**
     * 微信登录
     */
    USER_WX(0, "微信登录"),
    /**
     * 邮箱登录
     */
    USER_Email(1, "邮箱登录");
    private final int code;
    private final String description;
}
