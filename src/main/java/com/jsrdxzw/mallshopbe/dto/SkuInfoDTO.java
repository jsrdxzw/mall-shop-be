package com.jsrdxzw.mallshopbe.dto;

import lombok.Data;

import javax.validation.constraints.Positive;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@Data
public class SkuInfoDTO {
    private Long id;
    @Positive
    private Integer count;
}
