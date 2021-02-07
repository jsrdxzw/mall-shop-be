package com.jsrdxzw.mallshopbe.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@Data
public class OrderDTO {
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "99999999.99")
    private BigDecimal totalPrice;

    @DecimalMin(value = "0.00")
    @DecimalMax(value = "99999999.99")
    private BigDecimal finalTotalPrice;

    private Long couponId;

    @Valid
    private List<SkuInfoDTO> skuInfoList;

    private OrderAddressDTO address;
}
