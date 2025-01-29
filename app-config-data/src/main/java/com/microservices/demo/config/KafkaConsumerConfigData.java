package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka-consumer-config")
public record KafkaConsumerConfigData(String keyDeserializer,
                                      String valueDeserializer,
                                      String consumerGroupId,
                                      String autoOffsetReset,
                                      String specificAvroReaderKey,
                                      String specificAvroReader,
                                      Boolean batchListener,
                                      Boolean autoStartup,
                                      Integer concurrencyLevel,
                                      Integer sessionTimeoutMs,
                                      Integer heartbeatIntervalMs,
                                      Integer maxPollIntervalMs,
                                      Integer maxPollRecords,
                                      Integer maxPartitionFetchBytesDefault,
                                      Integer maxPartitionFetchBytesBoostFactor,
                                      Long pollTimeoutMs) {
}
