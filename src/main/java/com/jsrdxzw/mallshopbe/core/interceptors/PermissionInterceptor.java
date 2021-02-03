package com.jsrdxzw.mallshopbe.core.interceptors;

import com.jsrdxzw.mallshopbe.exception.UnAuthenticatedException;
import com.jsrdxzw.mallshopbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
public class PermissionInterceptor implements HandlerInterceptor {

    private static final String BEARER = "Bearer";
    private static final int TOKEN_LENGTH = 2;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ScopeLevel scopeLevel = getScopeLevel(handler);
        if (Objects.isNull(scopeLevel)) {
            return true;
        }
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasLength(bearerToken)) {
            throw new UnAuthenticatedException(10004);
        }
        if (!bearerToken.startsWith(BEARER)) {
            throw new UnAuthenticatedException(10004);
        }
        String[] tokens = bearerToken.split(" ");
        if (tokens.length != TOKEN_LENGTH) {
            throw new UnAuthenticatedException(10004);
        }
        String token = tokens[1];

        return false;
    }

    private ScopeLevel getScopeLevel(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            return handlerMethod.getMethodAnnotation(ScopeLevel.class);
        }
        return null;
    }
}
