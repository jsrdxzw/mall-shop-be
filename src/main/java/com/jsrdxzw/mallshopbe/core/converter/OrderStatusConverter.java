package com.jsrdxzw.mallshopbe.core.converter;

import com.jsrdxzw.mallshopbe.core.enumeration.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author xuzhiwei
 * @date 2021-02-05
 */
@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OrderStatus orderStatus) {
        return orderStatus.getStatus();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer status) {
        return OrderStatus.fromStatus(status);
    }
}
