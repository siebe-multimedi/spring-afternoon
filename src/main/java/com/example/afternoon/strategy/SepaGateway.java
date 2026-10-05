package com.example.afternoon.strategy;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

/**
 * Exercise 2 (solved). @Order(1): comes first in an injected List<PaymentGateway>.
 * @Primary: the one that wins when a single PaymentGateway is injected.
 */
@Primary
@Order(1)
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
