package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "elastic-query-web-client")
public record ElasticQueryWebClientConfigData(WebClient webClient, Query queryByText) {

    public record WebClient(
            Integer connectTimeoutMs,
            Integer readTimeoutMs,
            Integer writeTimeoutMs,
            Integer maxInMemorySize,
            String contentType,
            String acceptType,
            String baseUrl) {
    }

    public record Query(String method,
                        String accept,
                        String uri) {
    }

}