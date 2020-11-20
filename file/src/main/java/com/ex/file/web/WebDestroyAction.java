package com.ex.file.web;

import com.ex.file.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * 项目停止时，销毁连接池
 *
 */
@Component
public class WebDestroyAction {
    @Autowired
    private TaskManager taskManager;

    @PreDestroy
    public void destroy() {
        try {
            //关闭连接资源
            taskManager.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
