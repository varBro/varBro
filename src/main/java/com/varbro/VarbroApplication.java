package com.varbro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.varbro.*")
public class VarbroApplication {
    public static void main(String[] args) {
        SpringApplication.run(VarbroApplication.class, args);
    }
}
