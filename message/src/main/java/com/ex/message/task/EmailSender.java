package com.ex.message.task;

import com.ex.message.enums.EnumMessageStatus;
import com.ex.message.mapper.MessageMapper;
import com.ex.message.service.SendEmailService;
import com.ex.message.spring.config.MessageProperties;
import com.ex.model.entity.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class EmailSender implements Runnable {

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    MessageProperties messageProperties;

    private Logger logger = LoggerFactory.getLogger(EmailSender.class);

    public EmailSender(MessageMapper messageMapper, SendEmailService sendEmailService, MessageProperties messageProperties) {
        this.messageMapper = messageMapper;
        this.sendEmailService = sendEmailService;
        this.messageProperties = messageProperties;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                Message message = SenderTask.getEmailMessage();
                if (message != null) {
                    if (message.getFailTimes() >= 1) {
                        logger.info("正在重发失败邮件ID: " + message.getId());
                    } else {
                        logger.info("正在发送邮件ID: " + message.getId());
                    }
                    boolean b = sendEmailService.sendEmail(message);
                    if (b) {
                        messageMapper.updateMessage(message.getId(), EnumMessageStatus.SUCCESS.getCode(), null, Timestamp.valueOf(LocalDateTime.now()));
                    } else {
                        try {
                            messageMapper.updateMessage(message.getId(), EnumMessageStatus.FAIL.getCode(), 1, null);
                        } catch (Exception e) {
                            logger.error("EmailSender.run,发送邮件更新数据库失败:", e);
                        }
                        message.setFailTimes(message.getFailTimes() + 1);
                        if (message.getFailTimes() <= messageProperties.getMaxFailResendTime()) {
                            SenderTask.addMessage(message);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("EmailSender.run:", e);
            }
        }
    }

}
