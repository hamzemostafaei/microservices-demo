package com.microservices.demo.twitter.to.kafka.service.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JasyptConfigTest {

    @Test
    public void testJasyptConfig() {
        JasyptConfig jasyptConfig = new JasyptConfig();
        String encrypted = jasyptConfig.stringEncryptor().encrypt("$pringCloudP@ssword!");
        log.info("[{}]",encrypted);
    }
}