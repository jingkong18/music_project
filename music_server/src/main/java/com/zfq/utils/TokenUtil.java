package com.zfq.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName TokenUtil
 * @Author zfq
 * @Date 2025/12/14
 */
@Component
public class TokenUtil {

    private static TokenManager staticTokenManager;

    @Autowired
    private TokenManager tokenManager;

    @PostConstruct
    public void init() {
        staticTokenManager = tokenManager;
    }

    /**
     * 从token中获取用户名（静态方法，方便在任意地方调用）
     */
    public static String parseToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            return null;
        }
        // 去除Bearer前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return staticTokenManager.getUsernameFromToken(token);
    }

    /**
     * 验证token是否有效
     */
    public static boolean validateToken(String token) {
        String username = parseToken(token);
        return username != null;
    }
}