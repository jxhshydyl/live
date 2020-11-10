
package com.ex.util.encrypt;

import org.springframework.util.DigestUtils;

public class MD5Utils {

    public static String md5(String plain) {
        return DigestUtils.md5DigestAsHex(plain.getBytes()).toUpperCase();
    }

    public static String md5(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes).toUpperCase();
    }

    public static String md5WithSalt(String plain, String salt) {
        plain = plain + salt;
        return md5(plain);
    }

}
