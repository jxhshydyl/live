package com.ex.message.web;

import com.ex.message.task.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(value = 1)
public class WebInitializeAction implements ApplicationRunner {

	@Autowired
	TaskManager taskManager;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		taskManager.start();
	}
	
}
