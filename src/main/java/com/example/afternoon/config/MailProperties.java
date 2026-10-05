package com.example.afternoon.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Exercise 6 (block 5). Bound from the "mail:" section of application.yaml.
 * This class needs no @Component: @ConfigurationPropertiesScan on
 * AfternoonApplication registers it.
 */
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    private String host;
    private int port;

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
}
