package com.jsrdxzw.mallshopbe.biz.coupon;

import com.jsrdxzw.mallshopbe.bo.OrderSkuBo;
import com.jsrdxzw.mallshopbe.core.enumeration.CouponType;
import com.jsrdxzw.mallshopbe.dto.OrderDTO;
import com.jsrdxzw.mallshopbe.exception.ForbiddenException;
import com.jsrdxzw.mallshopbe.exception.ParameterException;
import com.jsrdxzw.mallshopbe.model.BaseEntity;
import com.jsrdxzw.mallshopbe.model.Coupon;
import com.jsrdxzw.mallshopbe.util.CommonUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
public class CouponChecker {

    private final Coupon coupon;
    private final ICouponDiscount couponDiscount;

    public CouponChecker(Coupon coupon, ICouponDiscount couponDiscount) {
        this.coupon = coupon;
        this.couponDiscount = couponDiscount;
    }

    public void isOk(OrderDTO orderDTO, List<OrderSkuBo> orderSkuList, BigDecimal finalServerTotalPrice) {
        Date now = new Date();
        boolean isInTimeline = CommonUtil.isInTime(coupon.getStartTime(), now, coupon.getEndTime());
        if (!isInTimeline) {
            throw new ForbiddenException(40007);
        }
        couponCanBeUsed(orderSkuList, finalServerTotalPrice);
        discountPriceIsOk(orderDTO, finalServerTotalPrice);
    }

    private void discountPriceIsOk(OrderDTO orderDTO, BigDecimal finalServerTotalPrice) {
        BigDecimal clientDiscountTotalPrice = orderDTO.getFinalTotalPrice();
        BigDecimal serverDiscountTotalPrice;
        switch (CouponType.toType(coupon.getType())) {
            case FULL_MINUS:
            case NO_THRESHOLD_MINUS:
                serverDiscountTotalPrice = finalServerTotalPrice.subtract(coupon.getMinus());
                if (serverDiscountTotalPrice.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new ForbiddenException(50008);
                }
                break;
            case FULL_OFF:
                serverDiscountTotalPrice = couponDiscount.discount(finalServerTotalPrice, coupon.getRate());
                break;
            default:
                throw new ParameterException(40009);
        }
        if (serverDiscountTotalPrice.compareTo(clientDiscountTotalPrice) != 0) {
            throw new ForbiddenException(50008);
        }
    }

    private void couponCanBeUsed(List<OrderSkuBo> orderSkuList, BigDecimal finalServerTotalPrice) {
        BigDecimal orderCategoryPrice;
        if (coupon.getWholeStore()) {
            orderCategoryPrice = finalServerTotalPrice;
        } else {
            List<Long> categoryIdList = coupon.getCategoryList().stream().map(BaseEntity::getId)
                    .collect(Collectors.toList());
            orderCategoryPrice = getSumByCategoryIdList(orderSkuList, categoryIdList);
        }
        switch (CouponType.toType(coupon.getType())) {
            case FULL_OFF:
            case FULL_MINUS:
                if (orderCategoryPrice.compareTo(coupon.getFullMoney()) < 0) {
                    throw new ParameterException(40008);
                }
                break;
            default:
                break;
        }
    }

    private BigDecimal getSumByCategoryIdList(List<OrderSkuBo> orderSkuList, List<Long> categoryIdList) {
        return categoryIdList.stream().map(it -> orderSkuList.stream()
                .filter(orderSkuBo -> orderSkuBo.getCategoryId().equals(it))
                .map(OrderSkuBo::getFinalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
