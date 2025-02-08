package com.microservices.demo.elastic.query.web.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = "com.microservices.demo")
@ConfigurationPropertiesScan(basePackages = "com.microservices.demo")
public class ElasticQueryWEbClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticQueryWEbClientApplication.class, args);
    }
}
