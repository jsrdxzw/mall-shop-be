package com.jsrdxzw.mallshopbe.bo;

import com.jsrdxzw.mallshopbe.dto.SkuInfoDTO;
import com.jsrdxzw.mallshopbe.model.Sku;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-05
 */
@Data
public class OrderSkuBo {
    private Long id;
    private Long spuId;
    private BigDecimal finalPrice;
    private BigDecimal singlePrice;
    private List<String> specValues;
    private Integer count;
    private String img;
    private String title;
    private Long categoryId;

    public OrderSkuBo(Sku sku, SkuInfoDTO skuInfoDTO) {
        this.id = sku.getId();
        this.spuId = sku.getSpuId();
        this.singlePrice = sku.getRealPrice();
        this.finalPrice = sku.getRealPrice().multiply(new BigDecimal(skuInfoDTO.getCount()));
        this.count = skuInfoDTO.getCount();
        this.img = sku.getImg();
        this.title = sku.getTitle();
        this.specValues = sku.getSpecValueList();
        this.categoryId = sku.getCategoryId();
    }
}
