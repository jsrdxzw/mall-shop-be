package com.jsrdxzw.mallshopbe.vo;

import com.jsrdxzw.mallshopbe.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paging<T> {
    private Long total;
    private Integer count;
    private Integer page;
    private Integer totalPage;
    @Builder.Default
    private List<T> items = Collections.emptyList();

    public static <T> Paging<T> createPaging(Page<T> page) {
        return Paging.<T>builder()
                .total(page.getTotalElements())
                .count(page.getSize())
                .page(page.getNumber())
                .totalPage(page.getTotalPages())
                .items(page.getContent())
                .build();
    }

    public static <T, K> Paging<K> createPaging(Page<T> page, Supplier<K> supplier) {
        List<T> content = page.getContent();
        List<K> targetList = CommonUtil.copyBeanList(content, supplier);
        return Paging.<K>builder()
                .total(page.getTotalElements())
                .count(page.getSize())
                .page(page.getNumber())
                .totalPage(page.getTotalPages())
                .items(targetList)
                .build();
    }
}
