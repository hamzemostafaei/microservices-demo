package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gateway-service")
public record GatewayServiceConfigData(Long timeoutMs,
                                       Float failureRateThreshold,
                                       Float slowCallRateThreshold,
                                       Long slowCallDurationThreshold,
                                       Integer permittedNumOfCallsInHalfOpenState,
                                       Integer slidingWindowSize,
                                       Integer minNumberOfCalls,
                                       Long waitDurationInOpenState) {
}
