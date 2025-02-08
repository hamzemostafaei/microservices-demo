package com.microservices.demo.kafka.streams.service.api;

import com.microservices.demo.kafka.streams.service.model.KafkaStreamsResponseModel;
import com.microservices.demo.kafka.streams.service.runner.StreamsRunner;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class KafkaStreamsController {

    private final StreamsRunner<String, Long> kafkaStreamsRunner;

    @GetMapping("/get-word-count-by-word/{word}")
    public ResponseEntity<KafkaStreamsResponseModel> getWordCountByWord(@PathVariable @NotEmpty String word) {
        Long wordCount = kafkaStreamsRunner.getValueByKey(word);

        log.info("Word count [{}] returned for word [{}]", wordCount, word);

        return ResponseEntity.ok(new KafkaStreamsResponseModel(word, wordCount));
    }
}
