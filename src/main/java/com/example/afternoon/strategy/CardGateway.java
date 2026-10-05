package com.example.afternoon.strategy;

/**
 * Exercise 2. No @Component on purpose: the tests register the classes themselves.
 */
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
