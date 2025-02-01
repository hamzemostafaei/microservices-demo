package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "user-config")
public record UserConfigData(String username, String password, String[] roles) {
}
