package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "twitter-to-kafka-service")
public record TwitterToKafkaServiceConfigData(List<String> twitterKeywords,
                                              String welcomeMessage,
                                              Boolean enableMockTweets,
                                              Integer mockMinTweetLength,
                                              Integer mockMaxTweetLength,
                                              Long mockSleepTimeMs) {
}
