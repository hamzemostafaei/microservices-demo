package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka-streams-config")
public record KafkaStreamsConfigData(String applicationID,
                                     String inputTopicName,
                                     String outputTopicName,
                                     String stateFileLocation,
                                     String wordCountStoreName) {
}
