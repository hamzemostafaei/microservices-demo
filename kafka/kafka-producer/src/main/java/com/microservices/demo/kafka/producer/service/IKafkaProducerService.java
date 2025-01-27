package com.microservices.demo.kafka.producer.service;

import org.apache.avro.specific.SpecificRecordBase;

import java.io.Serializable;

public interface IKafkaProducerService<K extends Serializable, V extends SpecificRecordBase> {

    void send(String topicName, K key, V message);
}
