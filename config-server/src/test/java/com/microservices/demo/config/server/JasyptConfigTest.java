package com.microservices.demo.config.server;

import com.microservices.demo.config.server.config.JasyptConfig;

class JasyptConfigTest {
    public static void main(String[] args) {
        JasyptConfig jasyptConfig = new JasyptConfig();
        String encrypted = jasyptConfig.stringEncryptor().encrypt("hamzemostafaei");
        System.out.println(encrypted);
    }
}