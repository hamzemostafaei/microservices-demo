package com.microservices.demo.elastic.query.service.data.access.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.microservices.demo"})
@EntityScan(basePackages = "com.microservices.demo")
public class RepositoryConfiguration {
}
