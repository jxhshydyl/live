package com.ex.model.constant;

/**
 * @Version: 1.0
 * @Description:
 */
public class RedisTimeConstant {
    /**
     * redis 中存储短信的过期时间  单位分钟
     */
    public static final Integer MESSAGE_EXPIRE = 10;

    /**
     * redis 中存储短信的过期时间  单位小时
     */
    public static final Integer LOGIN_PASSWORD_ERROR_LOCK = 24;
}
