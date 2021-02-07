package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * findByOpenid
     *
     * @param openid
     * @return
     */
    User findByOpenid(String openid);
}
