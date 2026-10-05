package com.example.afternoon.tx;

/**
 * A CHECKED exception (it extends Exception, not RuntimeException).
 */
public class PaymentRejectedException extends Exception {

    public PaymentRejectedException(String message) {
        super(message);
    }
}
