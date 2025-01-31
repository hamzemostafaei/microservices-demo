package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "elastic-query-config")
public record ElasticQueryConfigData(String textField) {
}
