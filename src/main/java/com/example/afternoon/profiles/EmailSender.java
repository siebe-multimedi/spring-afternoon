package com.example.afternoon.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class EmailSender implements MessageSender {

    @Override
    public String describe() {
        return "email sender";
    }
}
