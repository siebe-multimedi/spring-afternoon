package com.example.afternoon.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ConsoleSender implements MessageSender {

    @Override
    public String describe() {
        return "console sender";
    }
}
