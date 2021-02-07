package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.dto.TokenGetDTO;
import com.jsrdxzw.mallshopbe.exception.ParameterException;
import com.jsrdxzw.mallshopbe.http.WxHttpRest;
import com.jsrdxzw.mallshopbe.model.User;
import com.jsrdxzw.mallshopbe.repository.UserRepository;
import com.jsrdxzw.mallshopbe.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Service
public class WxAuthenticationService {
    @Autowired
    private WxHttpRest wxHttpRest;
    @Autowired
    private UserRepository userRepository;

    public String login(TokenGetDTO userData) {
        Map<String, Object> session = wxHttpRest.code2Session(userData.getAccount());
        return registerUser(session);
    }

    private String registerUser(Map<String, Object> session) {
        String openid = (String)session.get("openid");
        if (openid == null){
            throw new ParameterException(20004);
        }
        User user = this.userRepository.findByOpenid(openid);
        if (Objects.nonNull(user)) {
            return JwtToken.makeToken(user.getId());
        }
        user = new User();
        user.setOpenid(openid);
        userRepository.save(user);
        return JwtToken.makeToken(user.getId());
    }
}
