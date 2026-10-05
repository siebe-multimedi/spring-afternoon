package com.example.afternoon.lifecycle;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Exercise 3 (block 3), step 2 (solved).
 *
 * The constructor runs before field injection, so it cannot use "settings".
 * The work moved to @PostConstruct, which runs once the bean is fully wired.
 * (Constructor injection of AppSettings would fix it as well.)
 */
@Component
public class ReportHeader {

    @Autowired
    private AppSettings settings;

    private String title;

    @PostConstruct
    public void init() {
        this.title = settings.getGreeting().toUpperCase();
        System.out.println("[lifecycle] ReportHeader ready: " + title);
    }
}
