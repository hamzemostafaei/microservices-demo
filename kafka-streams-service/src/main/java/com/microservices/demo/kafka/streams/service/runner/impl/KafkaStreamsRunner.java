package com.microservices.demo.kafka.streams.service.runner.impl;

import com.microservices.demo.config.KafkaConfigData;
import com.microservices.demo.config.KafkaStreamsConfigData;
import com.microservices.demo.kafka.avro.model.TwitterAnalyticsAvroModel;
import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import com.microservices.demo.kafka.streams.service.runner.StreamsRunner;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaStreamsRunner implements StreamsRunner<String, Long> {

    private static final String REGEX = "\\W+";

    private final KafkaStreamsConfigData kafkaStreamsConfigData;

    private final KafkaConfigData kafkaConfigData;

    private final Properties streamsConfiguration;

    private KafkaStreams kafkaStreams;

    private volatile ReadOnlyKeyValueStore<String, Long> keyValueStore;


    @Override
    public void start() {
        final Map<String, String> serdeConfig = Collections.singletonMap(
                kafkaConfigData.schemaRegistryUrlKey(),
                kafkaConfigData.schemaRegistryUrl()
        );

        final StreamsBuilder streamsBuilder = new StreamsBuilder();

        final KStream<Long, TwitterAvroModel> twitterAvroModelKStream = getTwitterAvroModelKStream(serdeConfig, streamsBuilder);

        createTopology(twitterAvroModelKStream, serdeConfig);

        startStreaming(streamsBuilder);

    }

    @Override
    public Long getValueByKey(String word) {
        if (kafkaStreams != null && kafkaStreams.state() == KafkaStreams.State.RUNNING) {
            if (keyValueStore == null) {
                synchronized (this) {
                    if (keyValueStore == null) {
                        keyValueStore = kafkaStreams.store(
                                StoreQueryParameters.fromNameAndType(
                                        kafkaStreamsConfigData.wordCountStoreName(),
                                        QueryableStoreTypes.keyValueStore()
                                )
                        );
                    }
                }
            }
            return keyValueStore.get(word.toLowerCase());
        }
        return 0L;
    }

    private void startStreaming(StreamsBuilder streamsBuilder) {
        final Topology topology = streamsBuilder.build();

        log.info("Defined topology: [{}]", topology.describe());

        kafkaStreams = new KafkaStreams(topology, streamsConfiguration);
        kafkaStreams.start();

        log.info("Kafka streaming started..");
    }


    private void createTopology(KStream<Long, TwitterAvroModel> twitterAvroModelKStream, Map<String, String> serdeConfig) {

        Pattern pattern = Pattern.compile(REGEX, Pattern.UNICODE_CHARACTER_CLASS);

        Serde<TwitterAnalyticsAvroModel> serdeTwitterAnalyticsAvroModel = getSerdeAnalyticsModel(serdeConfig);

        twitterAvroModelKStream
                .flatMapValues(value -> Arrays.asList(pattern.split(value.getText().toLowerCase())))
                .groupBy((key, word) -> word)
                .count(Materialized.as(kafkaStreamsConfigData.wordCountStoreName()))
                .toStream()
                .map(mapToAnalyticsModel())
                .to(kafkaStreamsConfigData.outputTopicName(), Produced.with(Serdes.String(), serdeTwitterAnalyticsAvroModel));

    }

    private KeyValueMapper<String, Long, KeyValue<? extends String, ? extends TwitterAnalyticsAvroModel>> mapToAnalyticsModel() {

        return (word, count) -> {

            log.info("Sending to topic [{}], word [{}] - count [{}]", kafkaStreamsConfigData.outputTopicName(), word, count);

            return new KeyValue<>(word, TwitterAnalyticsAvroModel
                    .newBuilder()
                    .setWord(word)
                    .setWordCount(count)
                    .setCreatedAt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                    .build());
        };
    }

    private Serde<TwitterAnalyticsAvroModel> getSerdeAnalyticsModel(Map<String, String> serdeConfig) {
        Serde<TwitterAnalyticsAvroModel> serdeTwitterAnalyticsAvroModel = new SpecificAvroSerde<>();
        serdeTwitterAnalyticsAvroModel.configure(serdeConfig, false);
        return serdeTwitterAnalyticsAvroModel;
    }

    private KStream<Long, TwitterAvroModel> getTwitterAvroModelKStream(Map<String, String> serdeConfig, StreamsBuilder streamsBuilder) {

        final Serde<TwitterAvroModel> serdeTwitterAvroModel = new SpecificAvroSerde<>();
        serdeTwitterAvroModel.configure(serdeConfig, false);

        return streamsBuilder.stream(kafkaStreamsConfigData.inputTopicName(), Consumed.with(Serdes.Long(), serdeTwitterAvroModel));
    }

}
