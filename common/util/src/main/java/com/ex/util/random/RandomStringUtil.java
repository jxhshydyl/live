package com.ex.util.random;

import java.util.Random;

/**
 * @Classname RandomStringUtil
 * @Description
 * @Date 2020/11/3 20:43
 */
public class RandomStringUtil {

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
