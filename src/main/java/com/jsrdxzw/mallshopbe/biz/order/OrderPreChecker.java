package com.jsrdxzw.mallshopbe.biz.order;

import com.jsrdxzw.mallshopbe.biz.coupon.CouponChecker;
import com.jsrdxzw.mallshopbe.bo.OrderSkuBo;
import com.jsrdxzw.mallshopbe.dto.OrderDTO;
import com.jsrdxzw.mallshopbe.dto.SkuInfoDTO;
import com.jsrdxzw.mallshopbe.exception.ParameterException;
import com.jsrdxzw.mallshopbe.model.Sku;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@Getter
public class OrderPreChecker {

    private final OrderDTO orderDTO;
    private final List<Sku> skuList;
    private final CouponChecker couponChecker;
    private final int maxSkuLimit;
    private final Map<Long, SkuInfoDTO> skuInfoMap;
    private final List<OrderSkuBo> orderSkuList = new ArrayList<>();

    public OrderPreChecker(OrderDTO orderDTO, List<Sku> skuList, CouponChecker couponChecker, int maxSkuLimit) {
        this.orderDTO = orderDTO;
        this.skuList = skuList;
        this.couponChecker = couponChecker;
        this.maxSkuLimit = maxSkuLimit;
        skuInfoMap = orderDTO.getSkuInfoList().stream()
                .collect(Collectors.toMap(SkuInfoDTO::getId, Function.identity()));
    }

    public OrderPreChecker isOk() {
        BigDecimal finalServerTotalPrice = BigDecimal.ZERO;
        for (Sku sku : skuList) {
            SkuInfoDTO skuInfoDTO = skuInfoMap.get(sku.getId());
            certainNotSellOut(sku);
            certainNotBeyondStock(sku, skuInfoDTO);
            certainNotMaxLimit(skuInfoDTO);
            finalServerTotalPrice = finalServerTotalPrice.add(calculatePrice(sku, skuInfoDTO));
            orderSkuList.add(new OrderSkuBo(sku, skuInfoDTO));
        }
        if (orderDTO.getTotalPrice().compareTo(finalServerTotalPrice) != 0) {
            throw new ParameterException(50005);
        }
        if (Objects.nonNull(couponChecker)) {
            couponChecker.isOk(orderDTO, orderSkuList, finalServerTotalPrice);
        } else {
            if (orderDTO.getFinalTotalPrice().compareTo(finalServerTotalPrice) != 0) {
                throw new ParameterException(50005);
            }
        }
        return this;
    }

    public Integer getTotalCount() {
        return orderDTO.getSkuInfoList().stream().mapToInt(SkuInfoDTO::getCount).sum();
    }

    public String getLeaderImg() {
        return this.skuList.get(0).getImg();
    }

    public String getLeaderTitle() {
        return this.skuList.get(0).getTitle();
    }

    private BigDecimal calculatePrice(Sku sku, SkuInfoDTO skuInfoDTO) {
        return sku.getRealPrice().multiply(BigDecimal.valueOf(skuInfoDTO.getCount()));
    }

    private void certainNotSellOut(Sku sku) {
        if (sku.getStock() == 0) {
            throw new ParameterException(50003);
        }
    }

    private void certainNotBeyondStock(Sku sku, SkuInfoDTO skuInfoDTO) {
        if (sku.getStock() < skuInfoDTO.getCount()) {
            throw new ParameterException(50003);
        }
    }

    private void certainNotMaxLimit(SkuInfoDTO skuInfoDTO) {
        if (skuInfoDTO.getCount() > this.maxSkuLimit) {
            throw new ParameterException(50004);
        }
    }
}
