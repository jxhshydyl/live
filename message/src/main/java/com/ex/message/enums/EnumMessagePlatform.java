package com.ex.message.enums;

/**
 * @Date: 5/6/2020 下午 9:34
 * @Version: 1.0
 * @Description:
 */
public enum EnumMessagePlatform {
    //消息平台（1：美联，2：赛邮）
    ML(1,"美联"),
    SY(2,"赛邮"),
    ;

    private Integer code;
    private String message;

    EnumMessagePlatform(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
