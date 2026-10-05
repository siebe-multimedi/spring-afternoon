package com.example.afternoon.lifecycle;

import org.springframework.stereotype.Component;

/**
 * Exercise 3 (block 3), step 3 and the bonus.
 *
 * STEP 3: open() must run once, right after the bean is wired, and close()
 * must run when the application shuts down. Add the two lifecycle annotations.
 */
@Component
public class ResourceHolder {

    public void open() {
        System.out.println("[lifecycle] resource opened");
    }

    public void close() {
        System.out.println("[lifecycle] resource closed");
    }
}
