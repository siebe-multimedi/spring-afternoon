package com.example.afternoon.tx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// Deliberately NOT @Transactional on the test class: a transactional test would
// roll back everything at the end and hide exactly what this exercise is about.
@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private PaymentService service;

    @Autowired
    private PaymentRepository payments;

    @BeforeEach
    void clean() {
        payments.deleteAll();
    }

    @Test
    void theInjectedServiceIsAProxy() {
        assertThat(AopUtils.isAopProxy(service)).isTrue();
    }

    @Test
    void anAcceptedPaymentIsStored() throws Exception {
        service.pay(50.0);

        assertThat(payments.count()).isEqualTo(1);
    }

    @Test
    void aRejectedPaymentIsNotStored() {
        assertThatThrownBy(() -> service.pay(2000.0))
                .isInstanceOf(PaymentRejectedException.class);

        assertThat(payments.count()).isZero();
    }
}
