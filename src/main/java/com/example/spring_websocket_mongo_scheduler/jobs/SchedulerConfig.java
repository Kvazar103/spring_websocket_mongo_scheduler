package com.example.spring_websocket_mongo_scheduler.jobs;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
public class SchedulerConfig {

    @Scheduled(fixedDelay = 1000) //означає що за кожну секунду буде виконуватися цей метод
    @Scheduled(fixedRate = 1000)//запускається кожну 1 секунду не чекаючи коли попередня таска закінчиться
    public void asdqew(){
        System.out.println("scheduled task");
    }
}
