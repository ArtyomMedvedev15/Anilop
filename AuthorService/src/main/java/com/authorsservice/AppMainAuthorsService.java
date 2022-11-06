package com.authorsservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppMainAuthorsService {
    public static void main(String[] args) {
        SpringApplication.run(AppMainAuthorsService.class,args);
    }
}
