package com.zfq.utils;

import java.util.Random;

/**
 * @ClassName RandomUtils
 * @Author zfq
 * @Date 2025/12/7
 */
public class RandomUtils {
    public static String code(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 6;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
