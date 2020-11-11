package com.ex.message.task;

import com.ex.message.enums.EnumMessageStatus;
import com.ex.message.mapper.MessageMapper;
import com.ex.message.spring.config.MessageProperties;
import com.ex.model.entity.message.Message;
import com.ex.model.enums.message.EnumMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * 定时获取短信放入到队列
 */
public class SenderTask implements Runnable {

    private MessageMapper messageMapper;

    private MessageProperties messageProperties;

    private static Integer LAST_ID = 0;

    private Logger logger = LoggerFactory.getLogger(SenderTask.class);

    private static Queue<Message> smsQueue = new ConcurrentLinkedQueue<Message>();
    private static Queue<Message> emailQueue = new ConcurrentLinkedQueue<Message>();

    public SenderTask(MessageMapper messageMapper,MessageProperties messageProperties) {
        this.messageMapper = messageMapper;
        this.messageProperties = messageProperties;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //增加分布式锁
                TimeUnit.MILLISECONDS.sleep(3000);
                List<Message> list;
                // 第一次取当前往前推5分钟开始的消息
                Integer lastId = LAST_ID;
                if (lastId == null || lastId == 0) {
                    LocalDateTime now = LocalDateTime.now();
                    list = messageMapper.selectNonSuccess(messageProperties.getMaxFailResendTime(),
                            Timestamp.valueOf(now.minusMinutes(5)),Timestamp.valueOf(now));
                } else {
                    list = messageMapper.selectGtIdNonSuccess(messageProperties.getMaxFailResendTime(),lastId);
                }
                if (list != null && list.size() > 0) {
                    lastId = list.get(0).getId();
                    logger.info("SenderTask.run取到{}条待发消息,lastId:{}", list.size(), lastId);
                    //使用redis存取
                    LAST_ID = lastId;
                    for (Message m : list) {
                        addMessage(m);
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static Message getEmailMessage() {
        return emailQueue.poll();
    }

    public static Message getSmsMessage() {
        return smsQueue.poll();
    }

    public static void addMessage(Message messageDO) {
        if (messageDO.getStatus() == EnumMessageStatus.SUCCESS.getCode().intValue()) {
            return;
        }
        if (messageDO.getType() == EnumMessageType.手机短信.getCode().intValue()) {
            smsQueue.offer(messageDO);
        } else {
            emailQueue.offer(messageDO);
        }
    }


}
