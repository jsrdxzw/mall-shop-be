package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.model.Category;
import com.jsrdxzw.mallshopbe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Pair<List<Category>, List<Category>> getAll() {
        List<Category> rootCategories = categoryRepository.findCategoriesByIsRootOrderByIndexAsc(true);
        List<Category> subCategories = categoryRepository.findCategoriesByIsRootOrderByIndexAsc(false);
        return Pair.of(rootCategories, subCategories);
    }
}
