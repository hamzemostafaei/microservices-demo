package com.microservices.demo.kafka.streams.service;

import com.microservices.demo.kafka.streams.service.init.IStreamsInitializer;
import com.microservices.demo.kafka.streams.service.runner.StreamsRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = "com.microservices.demo")
@ConfigurationPropertiesScan(basePackages = "com.microservices.demo")
public class KafkaStreamsServiceApplication implements CommandLineRunner {

    private final StreamsRunner<String, Long> streamsRunner;

    private final IStreamsInitializer streamsInitializer;

    public KafkaStreamsServiceApplication(StreamsRunner<String, Long> runner, IStreamsInitializer initializer) {
        this.streamsRunner = runner;
        this.streamsInitializer = initializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamsServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("App starts...");
        streamsInitializer.init();
        streamsRunner.start();

    }
}
