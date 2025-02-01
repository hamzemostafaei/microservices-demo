package com.microservices.demo.elastic.query.service.config;

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
        config.setPassword("n2r<EheXMXgy%gda~P`u-LaRyJ_$Ru/>hCa/#~@y&tNC4/kH^@FMP-R+9K.7p*<.TaNwz.&-H&i<?b:-ZsVgV77qh%79+YeH9`+$5i&xf;ym9^u3J4wx\\N+v/tHspzaD");
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

