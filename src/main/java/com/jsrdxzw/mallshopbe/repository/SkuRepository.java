package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
public interface SkuRepository extends JpaRepository<Sku, Long> {
    /**
     * 通过id查询sku
     *
     * @param idList
     * @return
     */
    List<Sku> findSkusByIdIn(List<Long> idList);
}
