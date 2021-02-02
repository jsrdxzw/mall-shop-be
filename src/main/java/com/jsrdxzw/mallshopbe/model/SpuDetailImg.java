package com.jsrdxzw.mallshopbe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class SpuDetailImg extends BaseEntity {
    private String img;

    private Long spuId;

    private Long index;
}
