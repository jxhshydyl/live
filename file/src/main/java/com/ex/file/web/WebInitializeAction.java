package com.ex.file.web;

import com.ex.file.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Spring 加载完之后调用
 *
 */
@Component
@Order(value = 1)
public class WebInitializeAction implements ApplicationRunner {
    @Autowired
    private TaskManager taskManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        taskManager.start();
    }

}
