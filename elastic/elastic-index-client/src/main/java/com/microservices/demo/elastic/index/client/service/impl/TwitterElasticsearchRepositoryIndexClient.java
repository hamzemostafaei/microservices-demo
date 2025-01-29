package com.microservices.demo.elastic.index.client.service.impl;

import com.microservices.demo.elastic.index.client.repository.ITwitterElasticsearchIndexRepository;
import com.microservices.demo.elastic.index.client.service.IElasticIndexClientService;
import com.microservices.demo.elastic.model.index.impl.TwitterIndexModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "elastic-config.is-repository", havingValue = "true", matchIfMissing = true)
public class TwitterElasticsearchRepositoryIndexClient implements IElasticIndexClientService<TwitterIndexModel> {

    private final ITwitterElasticsearchIndexRepository indexRepository;

    @Override
    public List<String> save(List<TwitterIndexModel> documents) {
        Iterable<TwitterIndexModel> indexedDocuments = indexRepository.saveAll(documents);

        List<String> documentIds = StreamSupport
                .stream(indexedDocuments.spliterator(), false)
                .map(TwitterIndexModel::getId)
                .toList();

        log.info("Successfully indexed [{}] documents of type [{}] with IDs: [{}]",
                documentIds.size(),
                TwitterIndexModel.class.getSimpleName(),
                documentIds);

        return documentIds;
    }
}
