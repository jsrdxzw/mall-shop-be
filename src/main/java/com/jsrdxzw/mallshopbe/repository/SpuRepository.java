package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.Spu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
public interface SpuRepository extends JpaRepository<Spu, Long> {
    /**
     * 根据分类id查询spu
     *
     * @param cid
     * @param pageable
     * @return
     */
    Page<Spu> findByCategoryIdOrderByCreateTimeDesc(Long cid, Pageable pageable);

    /**
     * 根据id查询spu
     *
     * @param idList
     * @return
     */
    List<Spu> findByIdIn(List<Long> idList);

    /**
     * 根据根分类id查询
     *
     * @param cid
     * @param pageable
     * @return
     */
    Page<Spu> findByRootCategoryIdOrderByCreateTimeDesc(Long cid, Pageable pageable);

    /**
     * 商品模糊查询
     *
     * @param title
     * @param subtitle
     * @param pageable
     * @return
     */
    Page<Spu> findByTitleLikeOrSubtitleLike(String title, String subtitle, Pageable pageable);

    /**
     * findPageOrderByCreateTimeDesc
     *
     * @param pageable
     * @return
     */
    Page<Spu> findAllByOrderByCreateTimeDesc(Pageable pageable);
}
