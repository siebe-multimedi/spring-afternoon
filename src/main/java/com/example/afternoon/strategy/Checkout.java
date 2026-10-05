package com.example.afternoon.strategy;

/**
 * Exercise 2, steps 3 and 4. Asks for ONE PaymentGateway, while there are two.
 */
public class Checkout {

    private final PaymentGateway gateway;

    public Checkout(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public String pay(double amount) {
        return gateway.charge(amount);
    }
}
