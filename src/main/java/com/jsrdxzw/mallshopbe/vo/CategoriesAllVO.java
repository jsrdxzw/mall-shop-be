package com.jsrdxzw.mallshopbe.vo;

import com.jsrdxzw.mallshopbe.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@Data
@NoArgsConstructor
public class CategoriesAllVO {
    private List<Category> roots;
    private List<Category> subs;

    public CategoriesAllVO(Pair<List<Category>, List<Category>> pair) {
        this.roots = pair.getFirst();
        this.subs = pair.getSecond();
    }
}
