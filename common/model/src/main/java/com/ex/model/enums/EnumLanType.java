package com.ex.model.enums;

/**
 * @Date: 03/09/2020 下午 6:46
 * @Version: 1.0
 * @Description: 语言类型
 */
public enum EnumLanType {

    CN_LAN("cn", "中文"),
    EN_LAN("en", "英文");

    private String code;
    private String name;

    EnumLanType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EnumLanType getByCode(String code) {
        EnumLanType[] types = EnumLanType.values();
        for (EnumLanType type : types) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
