package com.microservices.demo.twitter.to.kafka.service.listener;

import com.microservices.demo.config.KafkaConfigData;
import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import com.microservices.demo.kafka.producer.service.IKafkaProducerService;
import com.microservices.demo.twitter.to.kafka.service.transformer.TwitterStatusToAvroTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Slf4j
@Component
@RequiredArgsConstructor
public class TwitterKafkaStatusListener extends StatusAdapter {

    private final KafkaConfigData kafkaConfigData;
    private final IKafkaProducerService<Long, TwitterAvroModel> kafkaProducerService;
    private final TwitterStatusToAvroTransformer twitterStatusToAvroTransformer;

    @Override
    public void onStatus(Status status) {
        log.info("Received status text [{}] sending to kafka topic [{}]", status.getText(), kafkaConfigData.topicName());

        TwitterAvroModel twitterAvroModel = twitterStatusToAvroTransformer.getTwitterAvroModelFromStatus(status);
        kafkaProducerService.send(kafkaConfigData.topicName(), twitterAvroModel.getUserId(), twitterAvroModel);
    }
}
