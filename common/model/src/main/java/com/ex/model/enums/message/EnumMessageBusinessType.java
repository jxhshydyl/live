package com.ex.model.enums.message;


/**
 * 短信类型枚举类
 */
public enum EnumMessageBusinessType {
    REGISTER(101, "注册验证码",false),
    LOGIN_OTHER(102, "异地登录验证码",false),
    LOGIN_CODE(103, "登录验证码",false),
    FIND_LOGIN_PWD(104, "找回登陆密码验证码",false),
    FIND_SAFE_PWD(105, "找回安全密码验证码",true),
    AUTH_EMAIL_EMAIL(106, "认证邮箱邮箱验证码",true),
    AUTH_EMAIL_MOBILE(107, "认证邮箱手机验证码",true),
    AUTH_MOBILE_MOBILE(108, "认证手机手机验证码",true),
    AUTH_MOBILE_EMAIL(109, "认证手机邮箱验证码",true),
    UPDATE_MOBILE_EMAIL(110, "修改手机邮件验证码",true),
    UPDATE_MOBILE_OLD_MOBILE(111, "修改手机旧手机验证码",true),
    UPDATE_MOBILE_NEW_MOBILE(112, "修改手机新手机验证码",true),
    BINDING_BANK_CARD(113, "绑定银行卡验证码",true),
    WITHDRAW(114, "申请提现验证码",true),
    MODIFY_LOGIN_PWD(115, "修改登录密码验证码",true),
    MODIFY_SAFE_PWD(116, "修改安全密码验证码",true),
    ;
    private int id;
    private String name;
    private boolean needLogin;

    EnumMessageBusinessType(int id, String name,boolean needLogin) {
        this.id = id;
        this.name = name;
        this.needLogin = needLogin;
    }

    public static String getName(int id) {
        for (EnumMessageBusinessType c : EnumMessageBusinessType.values()) {
            if (c.id == id) {
                return c.name;
            }
        }
        return null;
    }

    public static EnumMessageBusinessType getMsgType(int id) {
        for (EnumMessageBusinessType c : EnumMessageBusinessType.values()) {
            if (c.id == id) {
                return c;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isNeedLogin() {
        return needLogin;
    }
}
