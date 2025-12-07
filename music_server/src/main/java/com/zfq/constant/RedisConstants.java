package com.zfq.constant;

/**
 * @ClassName RedisConstants
 * @Author zfq
 * @Date 2025/12/7
 */
public class RedisConstants {
    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final Long LOGIN_CODE_TTL = 2L;
    public static final String USER_LOGIN_KEY = "login:token:";
    public static final Long LOGIN_USER_TTL = 36000L;
}
