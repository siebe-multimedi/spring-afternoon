package com.example.afternoon.strategy;

public class SepaGateway implements PaymentGateway {

    @Override
    public String name() {
        return "sepa";
    }

    @Override
    public String charge(double amount) {
        return "sepa:" + amount;
    }
}
