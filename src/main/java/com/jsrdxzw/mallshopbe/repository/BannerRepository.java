package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xuzhiwei
 * @date 2021-02-01
 */
public interface BannerRepository extends JpaRepository<Banner, Long> {
    /**
     * findFirstByName
     *
     * @param name
     * @return
     */
    Banner findFirstByName(String name);
}
