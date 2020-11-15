package com.ex.model.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * @Classname UserExpRecord
 * @Description TODO
 * @Date 2020/11/15 15:41
 */
@Data
public class UserExpRecord {
    private Long id;
    private Long userId;
    private Integer taskId;
    private String taskContent;
    private Integer exp;
    private Date createTime;
}
