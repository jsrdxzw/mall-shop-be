package com.jsrdxzw.mallshopbe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Where(clause = "delete_time is null")
public class Category extends BaseEntity {
    private String name;
    private String description;
    private Boolean isRoot;

    private String img;

    private Long parentId;

    private Long index;
}
