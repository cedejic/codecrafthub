package com.codecrafthub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of Spring Boot application.
 */

@SpringBootApplication
public class CodeCraftHubApplication {

    public static void main(String[] args) {

        SpringApplication.run(
            CodeCraftHubApplication.class,
            args
        );
    }
}