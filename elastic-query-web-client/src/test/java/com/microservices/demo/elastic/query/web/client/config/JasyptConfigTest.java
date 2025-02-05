package com.microservices.demo.elastic.query.web.client.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class JasyptConfigTest {

    @Test
    public void testJasyptConfig() {
        JasyptConfig jasyptConfig = new JasyptConfig();
        String encrypted = jasyptConfig.stringEncryptor().encrypt("4GerhMLaVrYKohOsYY8daZS2l0ORVbZh");
        log.info("[{}]", encrypted);
    }
}