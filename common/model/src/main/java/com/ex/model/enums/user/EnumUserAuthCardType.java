package com.ex.model.enums.user;

/**
 * @Classname EnumUserAuthCardType
 * @Description TODO
 * @Date 2020/11/11 23:19
 */
public enum EnumUserAuthCardType {
    //1：身份证，2：护照
    IDENTITY_CARD(0,"身份证"),
    PASSPORT(1,"护照"),
    ;

    private Integer code;
    private String name;

    EnumUserAuthCardType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
