package com.example.afternoon.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Exercise 9: your own mini auto-configuration, the same pattern Spring Boot uses
 * for DataSourceAutoConfiguration.
 *
 * Right now it ALWAYS creates a ConsoleNotifier. Make the tests in
 * NotifierAutoConfigurationTest pass by adding the right conditions.
 */
@AutoConfiguration
public class NotifierAutoConfiguration {

    @Bean
    public Notifier notifier() {
        return new ConsoleNotifier();
    }
}
