package com.example.afternoon.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

/**
 * Exercise 6 (block 5) (solved). Bound from the "mail:" section of application.yaml.
 * @Validated + @NotBlank makes a missing or misspelled "host" key fail at startup
 * instead of silently leaving host null.
 */
@Validated
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    @NotBlank
    private String host;
    private int port;
    private int retries;
    private Duration connectionTimeout;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public Duration getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Duration connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}
