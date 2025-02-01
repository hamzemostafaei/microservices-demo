package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "elastic-query-web-client")
public record ElasticQueryWebClientConfigData(WebClient webClient) {

    public record WebClient(
            Integer connectTimeoutMs,
            Integer readTimeoutMs,
            Integer writeTimeoutMs,
            Integer maxInMemorySize,
            String contentType,
            String acceptType,
            String baseUrl) {
    }
}