package com.microservices.demo.elastic.query.service.security;

import com.microservices.demo.elastic.query.service.config.JasyptConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class JasyptConfigTest {

    @Test
    public void testJasyptConfig() {
        JasyptConfig jasyptConfig = new JasyptConfig();
        String encrypted = jasyptConfig.stringEncryptor().encrypt("123456");
        log.info("[{}]", encrypted);
    }
}