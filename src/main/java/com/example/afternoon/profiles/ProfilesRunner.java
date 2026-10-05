package com.example.afternoon.profiles;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Exercise 5 (block 4): prints one line describing which profile is active and what it
 * resulted in. ObjectProvider is used on purpose: with no matching profile
 * there is NO MessageSender bean, and this class still starts (bonus step).
 */
@Component
@Order(2)
public class ProfilesRunner implements CommandLineRunner {

    private final Environment environment;
    private final ObjectProvider<MessageSender> sender;

    public ProfilesRunner(Environment environment, ObjectProvider<MessageSender> sender) {
        this.environment = environment;
        this.sender = sender;
    }

    @Override
    public void run(String... args) {
        MessageSender active = sender.getIfAvailable();
        System.out.println("[profiles] active=" + Arrays.toString(environment.getActiveProfiles())
                + ", sender=" + (active == null ? "none" : active.describe())
                + ", greeting=" + environment.getProperty("app.greeting")
                + ", currency=" + environment.getProperty("app.currency"));
    }
}
