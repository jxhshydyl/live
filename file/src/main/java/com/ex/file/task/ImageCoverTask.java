package com.ex.file.task;

import com.ex.file.TaskManager;
import com.ex.file.enums.ImageSizeEnum;
import com.ex.file.service.ImageFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class ImageCoverTask implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(ImageCoverTask.class);

    private static Queue<String> imageQueue = new ConcurrentLinkedQueue<String>();


    public static void addImage(String filename){
        imageQueue.offer(filename);
    }
    public static String getImage(){
        return imageQueue.poll();
    }

    @Override
    public void run() {
        while (!TaskManager.isStop) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
                String filepath = getImage();
                if (filepath != null) {
                    // conver image to small medium large
                    logger.info("待转换图片: " + filepath);
                    String s = ImageFileService.resizeImageFile(filepath, ImageSizeEnum.SMALL);
                    logger.info("转换图片: " + s);
                    String s1 = ImageFileService.resizeImageFile(filepath, ImageSizeEnum.MEDIUM);
                    logger.info("转换图片: " + s1);
                    String s2 = ImageFileService.resizeImageFile(filepath, ImageSizeEnum.LARGE);
                    logger.info("转换图片: " + s2);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

    }
}
