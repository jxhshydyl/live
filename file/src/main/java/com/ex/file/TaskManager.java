package com.ex.file;

import com.ex.file.task.ImageCoverTask;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TaskManager {

    public static boolean isStop = false;

    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public void start() {
        executorService.execute(new ImageCoverTask());
    }

    public void stop() {
        isStop = true;
    }


}
