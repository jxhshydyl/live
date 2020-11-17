package com.ex.model.enums;

/**
 * @Classname EnumEither
 * @Description
 * @Date 2020/11/14 11:49
 */
public enum EnumEither {
    INVALID(0, "无效"),
    EFFECTIVE(1, "有效");
    private int code;
    private String msg;

    EnumEither(int code, String msg) {
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
