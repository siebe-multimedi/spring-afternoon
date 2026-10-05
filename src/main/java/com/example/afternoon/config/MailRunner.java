package com.example.afternoon.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class MailRunner implements CommandLineRunner {

    private final MailProperties mail;

    public MailRunner(MailProperties mail) {
        this.mail = mail;
    }

    @Override
    public void run(String... args) {
        System.out.println("[config] host=" + mail.getHost()
                + ", port=" + mail.getPort()
                + ", retries=" + mail.getRetries()
                + ", connectionTimeout=" + mail.getConnectionTimeout());
    }
}
