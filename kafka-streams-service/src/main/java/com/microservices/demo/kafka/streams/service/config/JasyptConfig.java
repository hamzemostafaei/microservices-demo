package com.microservices.demo.kafka.streams.service.config;

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
        config.setPassword("X/~*UJL^.3~>;FT]Ez3UgLz.NPwS_EN!kp;Fv\\p<UY/n-p`g<%p[T@h_:,k7c?2pFub#>9pH*9+j{CLWyK4k=WmTxkSbt?d@7V#gPLV]LMs\".PV5\"y;U4`Yg{-%a(FR:");
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

