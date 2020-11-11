package com.ex.message.service.impl;

import com.ex.message.service.SendEmailService;
import com.ex.message.spring.config.MessageProperties;
import com.ex.message.util.EmailKit;
import com.ex.model.entity.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * @Author: liuweipeng
 * @Date: 5/6/2020 下午 9:52
 * @Version: 1.0
 * @Description:
 */
@Service
public class SendEmailServiceImpl implements SendEmailService {

    Logger logger = LoggerFactory.getLogger(SendEmailServiceImpl.class);

    @Autowired
    MessageProperties messageProperties;

    @Override
    public Boolean sendEmail(Message message) {
        return doEmail(message);
    }

    private boolean doEmail(Message m) {
        try {
            EmailKit emailKit = new EmailKit(
                    messageProperties.getEmail().getEmailHost(),
                    messageProperties.getEmail().getEmailPort(),
                    messageProperties.getEmail().getEmailProtocol(),
                    messageProperties.getEmail().getEmailSslEnable(),
                    messageProperties.getEmail().getEmailUserName(),
                    messageProperties.getEmail().getEmailPassword()
            );
            emailKit.from(messageProperties.getEmail().getEmailNickName(), messageProperties.getEmail().getEmailFrom())
                    .to(m.getReceiveAddress())
                    .subject(m.getTitle())
                    .html(m.getContext())
                    .send();
            return true;
        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
