package com.jsrdxzw.mallshopbe.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.util.Pair;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
public class CommonUtil {
    private static final Integer MAX_RANDOM = 1000000;
    private static final Integer PAD_SIZE = MAX_RANDOM.toString().length();

    public static boolean isInTime(Date startTime, Date now, Date endTime) {
        return now.after(startTime) && now.before(endTime);
    }

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

    public static String generateOrderNum() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        String dateTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        int randNumber = rand.nextInt(MAX_RANDOM);
        String randInt = StringUtils.leftPad(Integer.toString(randNumber), PAD_SIZE, "0");
        return dateTime + randInt;
    }
}
