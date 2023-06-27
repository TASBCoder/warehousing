package com.work.warehouse.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronSendMessage {

    @Scheduled(fixedRate = 5 * 1000)   // 每分钟执行一次
    public void sendMessageToAdmin() {

        System.out.println("你好"+System.currentTimeMillis());
    }
}
