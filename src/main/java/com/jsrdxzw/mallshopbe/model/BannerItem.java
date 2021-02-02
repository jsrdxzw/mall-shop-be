package com.jsrdxzw.mallshopbe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

/**
 * @author xuzhiwei
 * @date 2021-02-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Where(clause = "delete_time is null")
public class BannerItem extends BaseEntity {
    private String img;
    private String keyword;
    private Short type;
    private Long bannerId;
    private String name;
}
