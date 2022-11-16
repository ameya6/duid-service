package org.data.config;

import lombok.extern.log4j.Log4j2;
import org.data.model.entity.DUIDAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@Log4j2
public class ApplicationConfig {


    @Autowired
    private DUIDAttributes duidAttributes;

    @Bean
    public LocalDateTime epochDateTime() {
        return LocalDateTime.of(duidAttributes.getYear(), duidAttributes.getMonth(),duidAttributes.getDay(), duidAttributes.getHour(), duidAttributes.getMinute());
    }
}
