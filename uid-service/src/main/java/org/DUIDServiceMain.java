package org;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.duid", "org.data"})
@Log4j2
public class DUIDServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(DUIDServiceMain.class, args);
    }
}
