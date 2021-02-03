package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.model.Sku;
import com.jsrdxzw.mallshopbe.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@Service
public class SkuService {
    @Autowired
    private SkuRepository skuRepository;

    public List<Sku> getSkuListByIds(List<Long> idList) {
        return skuRepository.findSkusByIdIn(idList);
    }
}
