package com.ex.user.enums;

/**
 * @Classname EnumUserType
 * @Description
 * @Date 2020/11/17 22:45
 */
public enum EnumUserType {
    //0：普通用户   1：讲师
    //用户名类型 1：手机，2：邮箱
    USER(0, "普通用户"),
    TEACHER(1, "讲师");
    private int code;
    private String msg;

    EnumUserType(int code, String msg) {
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
