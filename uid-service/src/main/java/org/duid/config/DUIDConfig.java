package org.duid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class DUIDConfig {

    @Bean
    public Random random() {
        return new Random();
    }
}
