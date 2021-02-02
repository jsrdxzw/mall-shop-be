package com.jsrdxzw.mallshopbe.vo;

import lombok.Data;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@Data
public class SpuSimplifyVO {
    private Long id;
    private String title;
    private String subtitle;
    private String img;
    private String forThemeImg;
    private String price;
    private String discountPrice;
    private String description;
    private String tags;
    private String sketchSpecId;
}
