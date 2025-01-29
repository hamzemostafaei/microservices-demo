package com.microservices.demo.kafka.to.elastic.service.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class JasyptConfigTest {
    @Test
    public void testJasyptConfig() {
        JasyptConfig jasyptConfig = new JasyptConfig();
        String encrypted = jasyptConfig.stringEncryptor().encrypt("$pringCloudP@ssword!");
        log.info("[{}]",encrypted);
    }
}