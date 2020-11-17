package com.ex.user.enums;

/**
 * @Classname EnumUserType
 * @Description
 * @Date 2020/11/17 22:45
 */
public enum EnumType {
    //用户名类型 1：手机，2：邮箱
    MOBILE(1, "手机"),
    EMAIL(2, "邮箱");
    private int code;
    private String msg;

    EnumType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
