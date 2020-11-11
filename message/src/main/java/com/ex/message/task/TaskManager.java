package com.ex.message.task;

import com.ex.message.mapper.MessageMapper;
import com.ex.message.service.SendEmailService;
import com.ex.message.service.SendMsService;
import com.ex.message.spring.config.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 任务管理器
 * 
 * @author robot.guo
 */
@Component
public class TaskManager {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageProperties messageProperties;

    @Autowired
    private SendMsService sendMsService;

    @Autowired
    private SendEmailService sendEmailService;

	public static boolean stop = false;

	private static Logger logger = LoggerFactory.getLogger(TaskManager.class);
	
	public static ExecutorService executorService =  new ThreadPoolExecutor(4,8,10,
            //设置拒绝策略
            TimeUnit.MINUTES,new LinkedBlockingQueue<>(50),Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            logger.error(r.toString() + " is discard! ");    // 输出日志后直接丢弃任务
        }
    });
	
	public void start(){
        // 定时获取短信到队列中
        executorService.execute(new SenderTask(messageMapper,messageProperties));
        // 发送队列,根据情况增加
        executorService.execute(new SmsSender(messageMapper,sendMsService,messageProperties));
        executorService.execute(new EmailSender(messageMapper,sendEmailService,messageProperties));
    }

	public void stop() {
		stop = true;
	}
}
