package com.ex.message.service;

import com.ex.model.entity.message.Message;

/**
 * @Date: 5/6/2020 下午 9:52
 * @Version: 1.0
 * @Description: 发送邮件
 */
public interface SendEmailService {

   Boolean sendEmail(Message message);

}
