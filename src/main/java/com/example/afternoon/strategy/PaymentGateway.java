package com.example.afternoon.strategy;

public interface PaymentGateway {

    String name();

    String charge(double amount);
}
