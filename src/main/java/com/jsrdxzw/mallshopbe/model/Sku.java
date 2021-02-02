package com.jsrdxzw.mallshopbe.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jsrdxzw.mallshopbe.util.JsonUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Where(clause = "delete_time is null and online = true")
public class Sku extends BaseEntity {
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Boolean online;
    private String img;
    private String title;
    private Long spuId;
    private Long categoryId;
    private Long rootCategoryId;

    @Column(columnDefinition = "json")
    private String specs;
    private String code;
    private Integer stock;

    public BigDecimal getRealPrice() {
        return Objects.isNull(discountPrice) ? price : discountPrice;
    }

    public List<Spec> getSpecs() {
        if (Objects.isNull(specs)) {
            return Collections.emptyList();
        }
        return JsonUtil.jsonToObject(specs, new TypeReference<>() {
        });
    }

    public void setSpecs(List<Spec> specs) {
        this.specs = JsonUtil.objectToJson(specs);
    }
}
