package com.ex.message.task;

import com.ex.message.enums.EnumMessageStatus;
import com.ex.message.mapper.MessageMapper;
import com.ex.message.service.SendMsService;
import com.ex.message.spring.config.MessageProperties;
import com.ex.model.entity.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class SmsSender implements Runnable {

    private Logger logger= LoggerFactory.getLogger(SmsSender.class);

    private SendMsService sendMsService;

    private MessageMapper messageMapper;

    private MessageProperties messageProperties;

    public SmsSender(MessageMapper messageMapper, SendMsService sendMsService, MessageProperties messageProperties) {
        this.messageMapper = messageMapper;
        this.sendMsService = sendMsService;
        this.messageProperties = messageProperties;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                Message message = SenderTask.getSmsMessage();
                if (message != null) {
                    if (message.getFailTimes() >= 1) {
                        logger.info("重发失败短信ID: " + message.getId());
                    } else {
                        logger.info("发送短信ID: " + message.getId());
                    }
                    boolean flag = sendMsService.sendMs(message);
                    if (flag) {
                        messageMapper.updateMessage(message.getId(), EnumMessageStatus.SUCCESS.getCode(),null, Timestamp.valueOf(LocalDateTime.now()));
                    } else {
                        try {
                            messageMapper.updateMessage(message.getId(), EnumMessageStatus.FAIL.getCode(),1,null);
                        }catch (Exception e){
                            logger.error("EmailSender.run,发送短信更新数据库失败:",e);
                        }
                        message.setFailTimes(message.getFailTimes()+1);
                        if(message.getFailTimes() <= messageProperties.getMaxFailResendTime()){
                            SenderTask.addMessage(message);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("SmsSender.run:",e);
            }
        }
    }

}
