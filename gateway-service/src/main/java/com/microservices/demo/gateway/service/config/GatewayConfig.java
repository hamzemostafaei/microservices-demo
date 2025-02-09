package com.microservices.demo.gateway.service.config;

import com.microservices.demo.config.GatewayServiceConfigData;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private static final String HEADER_FOR_KEY_RESOLVER = "Authorization";
    private final GatewayServiceConfigData gatewayServiceConfigData;

    @Bean(name = "authHeaderResolver")
    KeyResolver userKeyResolver() {
        return (exchange) -> Mono.just(Objects.requireNonNull(exchange
                .getRequest().getHeaders().getFirst(HEADER_FOR_KEY_RESOLVER))
        );
    }

    @Bean
    Customizer<ReactiveResilience4JCircuitBreakerFactory> circuitBreakerFactoryCustomizer() {
        return (reactiveResilience4JCircuitBreakerFactory) -> reactiveResilience4JCircuitBreakerFactory
                .configureDefault(id -> new Resilience4JConfigBuilder(id)
                        .timeLimiterConfig(TimeLimiterConfig.custom()
                                .timeoutDuration(Duration.ofMillis(gatewayServiceConfigData.timeoutMs()))
                                .build())
                        .circuitBreakerConfig(CircuitBreakerConfig.custom()
                                .failureRateThreshold(gatewayServiceConfigData.failureRateThreshold())
                                .slowCallRateThreshold(gatewayServiceConfigData.slowCallRateThreshold())
                                .slowCallDurationThreshold(Duration.ofMillis(gatewayServiceConfigData.slowCallDurationThreshold()))
                                .permittedNumberOfCallsInHalfOpenState(gatewayServiceConfigData.permittedNumOfCallsInHalfOpenState())
                                .slidingWindowSize(gatewayServiceConfigData.slidingWindowSize())
                                .minimumNumberOfCalls(gatewayServiceConfigData.minNumberOfCalls())
                                .waitDurationInOpenState(Duration.ofMillis(gatewayServiceConfigData.waitDurationInOpenState()))
                                .build())
                        .build());
    }

}
