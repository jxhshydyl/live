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
    private Integer type;
    private String countryCode;
    private String receiveAddress;
    private String title;
    private String context;
    private String ip;
}
