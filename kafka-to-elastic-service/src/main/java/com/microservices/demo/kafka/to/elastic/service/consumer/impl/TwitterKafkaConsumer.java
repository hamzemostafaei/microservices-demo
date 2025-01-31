package com.microservices.demo.kafka.to.elastic.service.consumer.impl;

import com.microservices.demo.config.KafkaConfigData;
import com.microservices.demo.config.KafkaConsumerConfigData;
import com.microservices.demo.elastic.index.client.service.IElasticIndexClientService;
import com.microservices.demo.elastic.model.index.impl.TwitterIndexModel;
import com.microservices.demo.kafka.admin.client.KafkaAdminClient;
import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import com.microservices.demo.kafka.to.elastic.service.consumer.IKafkaConsumer;
import com.microservices.demo.kafka.to.elastic.service.transformer.AvroToElasticModelTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service("TwitterKafkaConsumer")
public class TwitterKafkaConsumer implements IKafkaConsumer<Long, TwitterAvroModel> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private final KafkaAdminClient kafkaAdminClient;
    private final KafkaConfigData kafkaConfigData;
    private final KafkaConsumerConfigData kafkaConsumerConfigData;
    private final AvroToElasticModelTransformer avroToElasticModelTransformer;
    private final IElasticIndexClientService<TwitterIndexModel> twitterIndexClientService;

    @EventListener
    public void onAppStarted(ApplicationStartedEvent event) {
        kafkaAdminClient.checkTopicsCreated();
        log.info("Topics with name [{}] is ready for operation.", kafkaConfigData.topicNamesToCreate());
        Objects.requireNonNull(kafkaListenerEndpointRegistry.getListenerContainer(kafkaConsumerConfigData.consumerGroupId())).start();
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.consumer-group-id}", topics = "${kafka-config.topic-name}")
    public void receive(List<ConsumerRecord<Long, TwitterAvroModel>> messages) {
        log.info("Twitter topic received: [{}]", messages);

        List<TwitterAvroModel> avroModels = messages.stream().map(ConsumerRecord::value).toList();

        List<TwitterIndexModel> elasticModels = avroToElasticModelTransformer.getElasticModels(avroModels);

        twitterIndexClientService.save(elasticModels);

        log.info("Documents [{}] saved to elasticsearch.", elasticModels);
    }
}
