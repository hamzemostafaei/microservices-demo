package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "elastic-config")
public record ElasticConfigData(String indexName,
                                String connectionUrl,
                                Integer connectionTimeoutMs,
                                Integer socketTimeoutMs) {
}
