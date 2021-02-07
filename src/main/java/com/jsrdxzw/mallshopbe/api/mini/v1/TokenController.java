package com.jsrdxzw.mallshopbe.api.mini.v1;

import com.jsrdxzw.mallshopbe.dto.TokenGetDTO;
import com.jsrdxzw.mallshopbe.service.WxAuthenticationService;
import com.jsrdxzw.mallshopbe.util.JwtToken;
import com.jsrdxzw.mallshopbe.vo.UserTokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@RequestMapping("/token")
@RestController
public class TokenController {
    @Autowired
    private WxAuthenticationService wxAuthenticationService;

    @PostMapping
    public UserTokenVO getToken(@RequestBody @Validated TokenGetDTO tokenGetDTO) {
        String token = wxAuthenticationService.login(tokenGetDTO);
        return UserTokenVO.builder().token(token).build();
    }

    @PostMapping("/verify")
    public Map<String, Boolean> verify(@RequestBody UserTokenVO token) {
        Map<String, Boolean> map = new HashMap<>();
        Boolean valid = JwtToken.verifyToken(token.getToken());
        map.put("is_valid", valid);
        return map;
    }
}
