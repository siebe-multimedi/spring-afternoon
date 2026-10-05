package com.example.afternoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

// @ConfigurationPropertiesScan registers @ConfigurationProperties classes without needing
// @Component on them. Only the config package is scanned: the props exercise registers its
// own record inside its tests, so it must not end up in the full application context.
@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "com.example.afternoon.config")
public class AfternoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfternoonApplication.class, args);
    }
}
