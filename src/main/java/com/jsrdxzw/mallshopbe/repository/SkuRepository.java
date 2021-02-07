package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * 减库存
     *
     * @param id
     * @param count
     * @return
     */
    @Modifying
    @Query("update Sku s\n" +
            "set s.stock = s.stock - :count\n" +
            "where s.id = :id\n" +
            "and s.stock >= :count")
    int reduceStock(Long id, Integer count);

    /**
     * 归还库存
     *
     * @param id
     * @param count
     */
    @Modifying
    @Query("update Sku s set s.stock = s.stock + (:count)\n" +
            "where s.id = :sid")
    void recoverStock(@Param("sid") Long id, Integer count);
}
