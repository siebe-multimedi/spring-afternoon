package com.example.afternoon.lifecycle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Asks the container for a ResourceHolder twice. With the default scope that
 * is the same bean twice; it matters for the bonus step (prototype scope).
 */
@Component
@Order(1)
public class LifecycleRunner implements CommandLineRunner {

    private final ApplicationContext context;

    public LifecycleRunner(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) {
        ResourceHolder first = context.getBean(ResourceHolder.class);
        ResourceHolder second = context.getBean(ResourceHolder.class);
        System.out.println("[lifecycle] two lookups, same instance: " + (first == second));
    }
}
