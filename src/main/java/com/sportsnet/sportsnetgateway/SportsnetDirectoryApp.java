package com.sportsnet.sportsnetgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Profile;

@EnableEurekaServer
@SpringBootApplication
@Profile("directory")
public class SportsnetDirectoryApp {
    public static void main(String[] args) {
        SpringApplication.run(SportsnetDirectoryApp.class, args);
    }
}