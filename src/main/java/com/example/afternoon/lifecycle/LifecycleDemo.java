package com.example.afternoon.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Exercise 3 (block 3), step 1. Field injection on purpose: look at what the constructor
 * can and cannot see compared to @PostConstruct.
 */
@Component
public class LifecycleDemo {

    @Autowired
    private AppSettings settings;

    public LifecycleDemo() {
        System.out.println("[lifecycle] 1. constructor, settings injected yet? " + (settings != null));
    }

    @PostConstruct
    public void init() {
        System.out.println("[lifecycle] 2. @PostConstruct, settings injected yet? " + (settings != null));
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("[lifecycle] 3. @PreDestroy");
    }
}
