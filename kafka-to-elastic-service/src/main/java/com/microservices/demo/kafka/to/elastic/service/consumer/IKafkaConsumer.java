package com.microservices.demo.kafka.to.elastic.service.consumer;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.Serializable;
import java.util.List;

public interface IKafkaConsumer<K extends Serializable, V extends SpecificRecordBase> {
    void receive(List<ConsumerRecord<K, V>> messages);
}
