package com.example.afternoon.tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Exercise 8. Saves the payment first, then rejects amounts above 1000.
 * A rejected payment must NOT stay in the database.
 */
@Service
public class PaymentService {

    private final PaymentRepository payments;

    public PaymentService(PaymentRepository payments) {
        this.payments = payments;
    }

    @Transactional
    public void pay(double amount) throws PaymentRejectedException {
        payments.save(new Payment(amount));
        if (amount > 1000) {
            throw new PaymentRejectedException("Amount too high: " + amount);
        }
    }
}
