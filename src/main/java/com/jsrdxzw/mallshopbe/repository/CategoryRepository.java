package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * 获取分类
     *
     * @param isRoot
     * @return
     */
    List<Category> findCategoriesByIsRootOrderByIndexAsc(Boolean isRoot);
}
