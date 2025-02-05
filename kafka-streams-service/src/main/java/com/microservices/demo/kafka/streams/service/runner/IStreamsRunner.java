package com.microservices.demo.kafka.streams.service.runner;

public interface IStreamsRunner<K, V> {
    void start();

    default V getValueByKey(K key) {
        return null;
    }
}