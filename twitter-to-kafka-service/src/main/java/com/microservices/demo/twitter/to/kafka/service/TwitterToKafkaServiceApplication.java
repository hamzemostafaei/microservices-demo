package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.runner.IStreamRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties
@ComponentScan(basePackages = "com.microservices.demo")
@ConfigurationPropertiesScan(basePackages = "com.microservices.demo")
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private final IStreamRunner streamRunner;

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("TwitterToKafkaServiceApplication started");
        log.info("TwitterToKafkaServiceConfigData: {}", twitterToKafkaServiceConfigData.twitterKeywords());
        log.info("{}", twitterToKafkaServiceConfigData.welcomeMessage());
        streamRunner.start();
    }
}
