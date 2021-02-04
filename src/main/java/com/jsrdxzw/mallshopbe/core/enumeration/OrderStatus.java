package com.jsrdxzw.mallshopbe.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@AllArgsConstructor
@Getter
public enum OrderStatus {
    /**
     * 全部
     */
    All(0, "全部"),
    /**
     * 待支付
     */
    UNPAID(1, "待支付"),
    /**
     * 已支付
     */
    PAID(2, "已支付"),
    /**
     * 已发货
     */
    DELIVERED(3, "已发货"),
    /**
     * 已完成
     */
    FINISHED(4, "已完成"),
    /**
     * 已取消
     */
    CANCELED(5, "已取消");

    private final int status;
    private final String description;

    public static OrderStatus fromStatus(int status) {
        return Stream.of(OrderStatus.values())
                .filter(it -> it.status == status)
                .findAny()
                .orElse(null);
    }
}
