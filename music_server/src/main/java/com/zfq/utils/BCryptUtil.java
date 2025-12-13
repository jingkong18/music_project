package com.zfq.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @ClassName BCryptUtil
 * @Author zfq
 * @Date 2025/12/14
 */

@Component
public class BCryptUtil {
    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    /**
     * 对明文密码进行 BCrypt 加密
     * @param rawPassword 明文密码
     * @return 加密后的密码（包含盐值，长度为60位）
     */
    public static String encrypt(String rawPassword) {
        // 空值校验
        if (rawPassword == null || rawPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        return PASSWORD_ENCODER.encode(rawPassword);
    }

    /**
     * 验证明文密码与加密后的密码是否匹配
     * @param rawPassword 明文密码（前端传入）
     * @param encodedPassword 加密后的密码（数据库存储）
     * @return 是否匹配
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        // 空值校验
        if (rawPassword == null || rawPassword.trim().isEmpty() || encodedPassword == null) {
            return false;
        }
        return PASSWORD_ENCODER.matches(rawPassword, encodedPassword);
    }
}
