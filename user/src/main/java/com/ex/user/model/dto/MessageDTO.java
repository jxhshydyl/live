package com.ex.user.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Classname Message
 * @Description TODO
 * @Date 2020/11/11 18:50
 */
@Data
public class MessageDTO {
    /**
     * 发送短信的类型 EnumMessageBusinessType
     */
    private Integer businessType;
    private String countryCode;
    private String receiveAddress;
    private String ip;
}
