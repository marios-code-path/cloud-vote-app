package com.sportsnet.sportsnetgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Profile


@EnableDiscoveryClient
@SpringBootApplication
@Profile("webapp")
class SportsnetWebApp {
    fun main(args: Array<String>) {
        runApplication<SportsnetWebApp>(*args)
    }

//    @Bean
//    fun pollRoutes(): RouterFunction<ServerResponse> {
//        routes(GET("/list"), {})
//    }
}