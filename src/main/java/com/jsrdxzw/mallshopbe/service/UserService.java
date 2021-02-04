package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.exception.NotFoundException;
import com.jsrdxzw.mallshopbe.model.User;
import com.jsrdxzw.mallshopbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(20002));
    }
}
