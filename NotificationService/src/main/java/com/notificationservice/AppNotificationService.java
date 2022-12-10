package com.notificationservice;

import com.notificationservice.event.HobbyInventoryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class AppNotificationService {
    public static void main(String[] args) {
        SpringApplication.run(AppNotificationService.class,args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(HobbyInventoryEvent hobbyInventoryEvent){
        log.info("Received notification for hobby - {}", hobbyInventoryEvent.getHobbyid());
    }
}
