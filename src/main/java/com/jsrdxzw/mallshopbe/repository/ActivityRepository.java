package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    /**
     * findFirstByName
     *
     * @param name
     * @return
     */
    Optional<Activity> findFirstByName(String name);
}
