package com.example.afternoon.lifecycle;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Exercise 3 (block 3), step 2.
 *
 * STEP 2: uncomment @Component below, start the application and read the
 * error. Then fix the class (the fix belongs in @PostConstruct).
 */
// @Component
public class ReportHeader {

    @Autowired
    private AppSettings settings;

    private final String title;

    public ReportHeader() {
        this.title = settings.getGreeting().toUpperCase();
    }

    @PostConstruct
    public void init() {
        System.out.println("[lifecycle] ReportHeader ready: " + title);
    }
}
