package com.ex.model.enums.user;

/**
 * @Classname EnumUserAuthStatus
 * @Description
 * @Date 2020/11/11 23:04
 */
public enum EnumUserAuthStatus {
    //0:未提交，1:审核中，2:审核不通过，3:审核通过
    NOT_SUBMIT(0,"未提交"),
    AUDITING(1,"审核中"),
    REJECT(2,"2审核不通过"),
    PASS(3,"审核通过");

    private Integer code;
    private String name;

    EnumUserAuthStatus(Integer code, String name) {
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
