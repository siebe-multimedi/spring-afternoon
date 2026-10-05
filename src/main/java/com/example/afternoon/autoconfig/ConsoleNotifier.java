package com.example.afternoon.autoconfig;

public class ConsoleNotifier implements Notifier {

    @Override
    public String send(String message) {
        return "console: " + message;
    }
}
