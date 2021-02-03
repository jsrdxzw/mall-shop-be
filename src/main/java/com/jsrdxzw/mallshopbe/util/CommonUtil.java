package com.jsrdxzw.mallshopbe.util;

import org.springframework.beans.BeanUtils;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
public class CommonUtil {
    public static Pair<Integer, Integer> convertPageParams(Integer start, Integer count) {
        Integer page = start / count;
        return Pair.of(page, count);
    }

    public static <T, K> List<K> copyBeanList(List<T> sourceList, Supplier<K> targetSupplier) {
        return sourceList.stream().map(it -> {
            K target = targetSupplier.get();
            BeanUtils.copyProperties(it, target);
            return target;
        }).collect(Collectors.toList());
    }
}
