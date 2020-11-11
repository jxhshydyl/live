package com.ex.message.enums;

/**
 * @Date: 5/6/2020 下午 4:09
 * @Version: 1.0
 * @Description:
 */
public enum EnumMessageStatus {
    //发送状态（0待发送，1成功，-1失败）
    WAIT(0,"待发送"),
    SUCCESS(1,"成功"),
    FAIL(-1,"失败");
    EnumMessageStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    private Integer code;
    private String message;


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
