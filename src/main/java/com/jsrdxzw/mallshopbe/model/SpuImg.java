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
public class SpuImg extends BaseEntity {
    private String img;
    private Long spuId;
}
