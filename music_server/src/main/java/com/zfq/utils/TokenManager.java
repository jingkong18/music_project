package com.zfq.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.zfq.constant.RedisConstants.LOGIN_USER_TTL;
import static com.zfq.constant.RedisConstants.USER_LOGIN_KEY;

/**
 * @ClassName TokenManager
 * @Author zfq
 * @Date 2025/12/14
 */
@Component
public class TokenManager {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 生成Token（UUID）
    public String generateToken(String username) {
        String token = UUID.randomUUID().toString().replace("-", "");
        // 存入Redis：key=前缀+token，value=用户名，设置过期时间
        redisTemplate.opsForValue().set(USER_LOGIN_KEY + token, username, LOGIN_USER_TTL, TimeUnit.HOURS);
        return token;
    }

    // 从Redis中获取用户名（校验Token是否有效）
    public String getUsernameFromToken(String token) {
        return (String) redisTemplate.opsForValue().get(USER_LOGIN_KEY + token);
    }

    // 删除Token（退出登录）
    public void removeToken(String token) {
        redisTemplate.delete(USER_LOGIN_KEY + token);
    }

    // 刷新Token过期时间
    public void refreshToken(String token) {
        redisTemplate.expire(USER_LOGIN_KEY + token, LOGIN_USER_TTL, TimeUnit.SECONDS);
    }
}