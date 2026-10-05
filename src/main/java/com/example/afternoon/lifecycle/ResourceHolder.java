package com.example.afternoon.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

/**
 * Exercise 3 (block 3), step 3 and the bonus (solved).
 *
 * For the bonus: add @Scope("prototype") (org.springframework.context.annotation.Scope)
 * under @Component and run again.
 */
@Component
public class ResourceHolder {

    @PostConstruct
    public void open() {
        System.out.println("[lifecycle] resource opened");
    }

    @PreDestroy
    public void close() {
        System.out.println("[lifecycle] resource closed");
    }
}
