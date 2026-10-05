package com.example.afternoon.lifecycle;

import org.springframework.stereotype.Component;

@Component
public class AppSettings {

    public String getGreeting() {
        return "welcome";
    }
}
