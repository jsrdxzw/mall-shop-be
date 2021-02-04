package com.jsrdxzw.mallshopbe.api.mini.v1;

import com.jsrdxzw.mallshopbe.core.UnifyResponse;
import com.jsrdxzw.mallshopbe.core.interceptors.ScopeLevel;
import com.jsrdxzw.mallshopbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/wx_info")
    @ScopeLevel
    public UnifyResponse updateUserInfo(@RequestBody Map<String, Object> user) {
        userService.updateUserWxInfo(user);
        return UnifyResponse.createSuccess();
    }
}
