package com.jsrdxzw.mallshopbe.vo;

import com.jsrdxzw.mallshopbe.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@Data
@NoArgsConstructor
public class CategoryPureVO {
    private Long id;

    private String name;

    private Boolean isRoot;

    private String img;

    private Long parentId;

    private Long index;

    public CategoryPureVO(Category category) {
        BeanUtils.copyProperties(category, this);
    }
}
