package com.jsrdxzw.mallshopbe.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@Component
public class JwtToken {
    private static String jwtKey;
    private static Integer expiredTimeIn;
    private static Integer defaultScope;

    @Value("${mall.security.jwt-key}")
    public void setJwtKey(String jwtKey) {
        JwtToken.jwtKey = jwtKey;
    }

    @Value("${mall.security.token-expired-time}")
    public void setExpiredTimeIn(Integer expiredTimeIn) {
        JwtToken.expiredTimeIn = expiredTimeIn;
    }

    @Value("${mall.security.default-scope}")
    public void setDefaultScope(Integer defaultScope) {
        JwtToken.defaultScope = defaultScope;
    }

    @Nullable
    public static Map<String, Claim> getClaims(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJwt;
        try {
            decodedJwt = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
        return decodedJwt.getClaims();
    }

    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    public static String makeToken(Long uid, Integer scope) {
        return generateToken(uid, scope);
    }

    public static String makeToken(Long uid) {
        return generateToken(uid, defaultScope);
    }

    private static String generateToken(Long uid, Integer scope) {
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.SECOND, expiredTimeIn);
        return JWT.create()
                .withClaim("uid", uid)
                .withClaim("scope", scope)
                .withExpiresAt(calendar.getTime())
                .withIssuedAt(now)
                .sign(algorithm);
    }
}
