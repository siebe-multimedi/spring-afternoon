package com.example.afternoon.strategy;

import java.util.List;
import java.util.Map;

/**
 * Exercise 2 (solved).
 */
public class PaymentRouter {

    private final Map<String, PaymentGateway> gateways;
    private final List<PaymentGateway> ordered;

    public PaymentRouter(Map<String, PaymentGateway> gateways, List<PaymentGateway> ordered) {
        this.gateways = gateways;
        this.ordered = ordered;
    }

    public String pay(String gatewayName, double amount) {
        PaymentGateway gateway = gateways.get(gatewayName);
        if (gateway == null) {
            throw new IllegalArgumentException("Unknown gateway: " + gatewayName);
        }
        return gateway.charge(amount);
    }

    public List<String> gatewayOrder() {
        return ordered.stream().map(PaymentGateway::name).toList();
    }
}
