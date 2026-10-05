package com.example.afternoon.tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Exercise 8 (solved). By default Spring rolls back only for unchecked exceptions
 * (RuntimeException and Error). PaymentRejectedException is checked, so it has to be
 * listed explicitly in rollbackFor.
 */
@Service
public class PaymentService {

    private final PaymentRepository payments;

    public PaymentService(PaymentRepository payments) {
        this.payments = payments;
    }

    @Transactional(rollbackFor = PaymentRejectedException.class)
    public void pay(double amount) throws PaymentRejectedException {
        payments.save(new Payment(amount));
        if (amount > 1000) {
            throw new PaymentRejectedException("Amount too high: " + amount);
        }
    }
}
