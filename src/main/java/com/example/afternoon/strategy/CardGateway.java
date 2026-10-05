package com.example.afternoon.strategy;

import org.springframework.core.annotation.Order;

/**
 * Exercise 2 (solved). @Order(2): comes second in an injected List<PaymentGateway>.
 */
@Order(2)
public class CardGateway implements PaymentGateway {

    @Override
    public String name() {
        return "card";
    }

    @Override
    public String charge(double amount) {
        return "card:" + amount;
    }
}
