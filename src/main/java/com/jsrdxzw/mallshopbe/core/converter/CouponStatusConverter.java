package com.jsrdxzw.mallshopbe.core.converter;

import com.jsrdxzw.mallshopbe.core.enumeration.CouponStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author xuzhiwei
 * @date 2021-02-05
 */
@Converter(autoApply = true)
public class CouponStatusConverter implements AttributeConverter<CouponStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CouponStatus couponStatus) {
        return couponStatus.getStatus();
    }

    @Override
    public CouponStatus convertToEntityAttribute(Integer integer) {
        return CouponStatus.toType(integer);
    }
}
