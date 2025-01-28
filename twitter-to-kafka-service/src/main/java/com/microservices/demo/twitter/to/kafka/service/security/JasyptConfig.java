package com.microservices.demo.twitter.to.kafka.service.security;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.cloud.bootstrap.BootstrapConfiguration;
import org.springframework.context.annotation.Bean;

@BootstrapConfiguration
public class JasyptConfig {

    @Bean(name = "propertiesEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("j2mhUptH7=7dm),<F8mW;9ISn=+mIO^#UT)Z9w~C_p2lq98r^]R\"r[#M=jXql`2`)k-H4W-fS#UXHF*Hc*KuM2p~cCFuWy\\HA\"fND8th<OO/v5qH<;F=[=3jwb*QJ]?B");
        config.setAlgorithm("PBEWithHmacSHA256AndAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGenerator(new RandomIvGenerator());
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }
}

