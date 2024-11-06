package com.buana.technical_test_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(
        basePackages = {"com.buana.technical_test_backend.repository"}
)
public class TechnicalTestBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnicalTestBackendApplication.class, args);
    }

}
