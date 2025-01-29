package com.microservices.demo.elastic.index.client.service;

import com.microservices.demo.elastic.model.index.IIndexModel;

import java.util.List;

public interface IElasticIndexClientService<T extends IIndexModel> {

    List<String> save(List<T> documents);
}
