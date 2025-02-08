package com.microservices.demo.discovery.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaServer
@SpringBootApplication
@ComponentScan(basePackages = "com.microservices.demo")
public class ServiceRegistrationAndDiscoveryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistrationAndDiscoveryServiceApplication.class, args);
    }
}
