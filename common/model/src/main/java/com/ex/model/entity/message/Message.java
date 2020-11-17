package com.ex.model.entity.message;

import lombok.Data;

import java.util.Date;

/**
 * @Classname Message
 * @Description
 * @Date 2020/11/11 18:50
 */
@Data
public class Message {
    private Integer id;
    private Integer type;
    private Long userId;
    private String userName;
    private String countryCode;
    private String receiveAddress;
    private String title;
    private String context;
    private Integer status;
    private Integer failTimes;
    private Date createTime;
    private String ip;
    private Date sendTime;
}
