package com.example.afternoon.props;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.List;

/**
 * Exercise 7 (solved).
 */
@Validated
@ConfigurationProperties(prefix = "smtp")
public record SmtpSettings(
        @NotBlank String host,
        @Min(1) @Max(65535) int port,
        @DefaultValue("3") int retries,
        @DefaultValue("5s") Duration timeout,
        List<Recipient> recipients) {

    public record Recipient(String name, String email) {
    }
}
