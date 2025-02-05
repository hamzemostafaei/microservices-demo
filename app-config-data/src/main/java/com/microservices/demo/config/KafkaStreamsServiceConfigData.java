package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka-streams-service")
public record KafkaStreamsServiceConfigData(String version, String customAudience) {
}
