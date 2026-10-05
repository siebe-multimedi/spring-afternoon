package com.example.afternoon.strategy;

import java.util.List;
import java.util.Map;

/**
 * Exercise 2. Spring can inject ALL beans of a type, as a Map (key = bean name)
 * or as a List (sorted by @Order).
 */
public class PaymentRouter {

    private final Map<String, PaymentGateway> gateways;
    private final List<PaymentGateway> ordered;

    public PaymentRouter(Map<String, PaymentGateway> gateways, List<PaymentGateway> ordered) {
        this.gateways = gateways;
        this.ordered = ordered;
    }

    /**
     * TODO: charge the gateway whose BEAN NAME is gatewayName (for example
     * "sepaGateway") and return what charge(...) returns. Throw an
     * IllegalArgumentException for an unknown name.
     */
    public String pay(String gatewayName, double amount) {
        throw new UnsupportedOperationException("TODO");
    }

    /** The names of the gateways, in the order Spring injected them. */
    public List<String> gatewayOrder() {
        return ordered.stream().map(PaymentGateway::name).toList();
    }
}
