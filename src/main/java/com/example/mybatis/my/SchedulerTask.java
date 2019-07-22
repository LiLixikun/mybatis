package com.example.mybatis.my;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Slf4j
public class SchedulerTask {

    private int count=0;

    private static final SimpleDateFormat date=new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 6000)
    public void SchedulerTask(){
        log.info("当前的coutn={}",count++);
        log.info("当前时间是={}",date);
    }
}
