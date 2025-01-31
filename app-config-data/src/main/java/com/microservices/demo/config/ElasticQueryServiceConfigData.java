package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "elastic-query-service")
public record ElasticQueryServiceConfigData(String version) {
}
