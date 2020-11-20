package com.ex.message.service;

import com.ex.model.entity.message.Message;

/**
 * @Date: 5/6/2020 下午 9:58
 * @Version: 1.0
 * @Description: 发送短信
 */
public interface SendMsService {

    boolean sendMs(Message record);

}
