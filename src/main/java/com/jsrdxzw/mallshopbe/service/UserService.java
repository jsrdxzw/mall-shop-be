package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.core.LocalUserFactory;
import com.jsrdxzw.mallshopbe.exception.NotFoundException;
import com.jsrdxzw.mallshopbe.model.User;
import com.jsrdxzw.mallshopbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

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

    @Transactional(rollbackFor = Exception.class)
    public void updateUserWxInfo(Map<String, Object> wxUser) {
        Long uid = LocalUserFactory.getUser().getId();
        User user = findById(uid);
        user.setNickname(wxUser.get("nickname").toString());
        user.setWxProfile(wxUser);
        userRepository.save(user);
    }
}
