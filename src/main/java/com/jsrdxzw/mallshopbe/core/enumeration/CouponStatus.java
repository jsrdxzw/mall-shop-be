package com.jsrdxzw.mallshopbe.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@Getter
@AllArgsConstructor
public enum CouponStatus {
    /**
     * 可以使用
     */
    AVAILABLE(1, "可以使用"),
    /**
     * 已使用
     */
    USED(2, "已使用"),
    /**
     * 未使用，已过期
     */
    EXPIRED(3, "未使用，已过期");

    private final int status;
    private final String description;

    public static CouponStatus toType(int status) {
        return Stream.of(CouponStatus.values())
                .filter(c -> c.status == status)
                .findFirst()
                .orElse(null);
    }
}
