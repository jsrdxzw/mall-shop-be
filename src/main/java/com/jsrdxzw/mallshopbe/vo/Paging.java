package com.jsrdxzw.mallshopbe.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@Data
@NoArgsConstructor
public class Paging<T> {
    private Long total;
    private Integer count;
    private Integer page;
    private Integer totalPage;
    private List<T> items = Collections.emptyList();

    public Paging(Page<T> page) {
        this.total = page.getTotalElements();
        this.count = page.getSize();
        this.page = page.getNumber();
        this.totalPage = page.getTotalPages();
        this.items = page.getContent();
    }

    public Paging(Page<?> page, Supplier<T> supplier) {
        this.total = page.getTotalElements();
        this.count = page.getSize();
        this.page = page.getNumber();
        this.totalPage = page.getTotalPages();
        List<?> content = page.getContent();
        this.items = content.stream().map(it -> {
            T target = supplier.get();
            BeanUtils.copyProperties(it, target);
            return target;
        }).collect(Collectors.toList());
    }
}
