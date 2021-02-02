package com.jsrdxzw.mallshopbe.util;

import org.springframework.data.util.Pair;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
public class CommonUtil {
    public static Pair<Integer, Integer> convertPageParams(Integer start, Integer count) {
        Integer page = start / count;
        return Pair.of(page, count);
    }
}
