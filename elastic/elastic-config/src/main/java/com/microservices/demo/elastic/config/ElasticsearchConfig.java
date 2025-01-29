package com.microservices.demo.elastic.config;

import com.microservices.demo.config.ElasticConfigData;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.support.HttpHeaders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@RequiredArgsConstructor
@EnableElasticsearchRepositories(basePackages = "com.microservices.demo.elastic")
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    private final ElasticConfigData elasticConfigData;

    @Bean
    @Override
    public ClientConfiguration clientConfiguration() {

        UriComponents serverUri = UriComponentsBuilder
                .fromUriString(elasticConfigData.connectionUrl())
                .build();

        return ClientConfiguration.builder()
                .connectedTo(serverUri.getHost() + ":" + serverUri.getPort())
                .withConnectTimeout(elasticConfigData.connectionTimeoutMs())
                .withSocketTimeout(elasticConfigData.socketTimeoutMs())
                .withHeaders(() -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Accept", "application/vnd.elasticsearch+json;compatible-with=8");
                    headers.add("Content-Type", "application/vnd.elasticsearch+json;compatible-with=8");
                    return headers;
                })
                .build();
    }
}
