package com.microservices.demo.elastic.query.client.repository;

import com.microservices.demo.elastic.model.index.impl.TwitterIndexModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ITwitterElasticsearchQueryRepository extends ElasticsearchRepository<TwitterIndexModel,String> {
    List<TwitterIndexModel> findByText(String text);
}
