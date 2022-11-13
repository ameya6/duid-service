package org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.duid"})
public class DUIDServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(DUIDServiceMain.class, args);
    }
}
