package com.zfq.utils;

import com.zfq.model.po.User;

/**
 * @ClassName UserHolder
 * @Author zfq
 * @Date 2025/12/7
 */
public class UserHolder {
    private static final ThreadLocal<User> tl = new ThreadLocal<>();

    public static void saveUser(User user) {
        tl.set(user);
    }

    public static User getUser() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}
