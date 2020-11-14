package com.ex.model.constant;

/**
 * @Version: 1.0
 * @Description: redis 中的key
 */
public class RedisKeyConstant {
    /**
     * redis 中存储短信的key
     */
    public static final String MESSAGE = "MESSAGE:";
    /**
     * redis 中存储登录密码错误次数
     */
    public static final String LOGIN_PASSWORD_ERROR = "login_password_error:";

    /**
     * redis 中存储登录密码错误次数
     */
    public static final String SSO_TOKEN = "sso_token:";

    /**
     * redis 中存储登录密码错误次数
     */
    public static final String SSO_SESSION = "sso_session:";
}
