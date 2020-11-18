package com.ex.user.enums;

import com.ex.model.enums.message.EnumMessageBusinessType;

/**
 * @Classname EnumMemberType
 * @Description
 * @Date 2020/11/18 22:55
 */
public enum EnumMemberType {
    //用户名类型 1：手机，2：邮箱
    MONTH(1, "月卡会员"),
    SEASON(2, "季卡会员"),
    HALF_YEAR(3, "半年会员"),
    YEAR(4, "一年会员");
    private int type;
    private String name;

    EnumMemberType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static EnumMemberType getType(int type) {
        for (EnumMemberType c : EnumMemberType.values()) {
            if (c.type == type) {
                return c;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
