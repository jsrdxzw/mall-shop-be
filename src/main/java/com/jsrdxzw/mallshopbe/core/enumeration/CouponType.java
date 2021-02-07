package com.jsrdxzw.mallshopbe.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author xuzhiwei
 * @date 2021-02-05
 */
@AllArgsConstructor
@Getter
public enum CouponType {
    /**
     * 满减券
     */
    FULL_MINUS(1, "满减券"),
    /**
     * 满减折扣券
     */
    FULL_OFF(2, "满减折扣券"),
    /**
     * 无门槛减除券
     */
    NO_THRESHOLD_MINUS(3, "无门槛减除券");
    private final int type;
    private final String description;

    public static CouponType toType(int type) {
        return Stream.of(CouponType.values())
                .filter(it -> it.type == type)
                .findFirst()
                .orElse(null);
    }
}
