package com.ex.model.enums.user;

/**
 * @Date: 16/11/2020 下午 6:11
 * @Version: 1.0
 * @Description:
 */
public enum UserFriendApplyStatus {
    //0：申请中  1：通过  2：拒绝
    APPLYING(0,"未提交"),
    PASS(1,"审核中"),
    REJECT(2,"2审核不通过");

    private Integer code;
    private String name;

    UserFriendApplyStatus(Integer code, String name) {
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
