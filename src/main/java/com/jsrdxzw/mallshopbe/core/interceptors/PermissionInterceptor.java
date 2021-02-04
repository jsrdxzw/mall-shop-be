package com.jsrdxzw.mallshopbe.core.interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.jsrdxzw.mallshopbe.core.LocalUserFactory;
import com.jsrdxzw.mallshopbe.exception.ForbiddenException;
import com.jsrdxzw.mallshopbe.exception.UnAuthenticatedException;
import com.jsrdxzw.mallshopbe.model.User;
import com.jsrdxzw.mallshopbe.service.UserService;
import com.jsrdxzw.mallshopbe.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
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
        Map<String, Claim> claims = JwtToken.getClaims(token);
        if (Objects.isNull(claims)) {
            throw new UnAuthenticatedException(10004);
        }
        boolean valid = hasPermission(scopeLevel, claims);
        if (valid) {
            setToThreadLocal(claims);
        }
        return valid;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LocalUserFactory.clear();
    }

    private void setToThreadLocal(Map<String, Claim> claims) {
        Long uid = claims.get("uid").asLong();
        Integer scope = claims.get("scope").asInt();
        User user = userService.findById(uid);
        LocalUserFactory.set(user, scope);
    }

    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> claims) {
        int level = scopeLevel.value();
        Integer scope = claims.get("scope").asInt();
        if (level > scope) {
            throw new ForbiddenException(10005);
        }
        return true;
    }

    private ScopeLevel getScopeLevel(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            return handlerMethod.getMethodAnnotation(ScopeLevel.class);
        }
        return null;
    }
}
