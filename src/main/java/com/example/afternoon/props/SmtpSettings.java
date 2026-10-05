package com.example.afternoon.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

/**
 * Exercise 7: a record as @ConfigurationProperties. Spring Boot binds records
 * through their constructor, no setters needed.
 *
 * Make the tests in SmtpSettingsTest pass: defaults for retries (3) and timeout (5s),
 * and validation for host and port.
 */
@ConfigurationProperties(prefix = "smtp")
public record SmtpSettings(
        String host,
        int port,
        int retries,
        Duration timeout,
        List<Recipient> recipients) {

    public record Recipient(String name, String email) {
    }
}
