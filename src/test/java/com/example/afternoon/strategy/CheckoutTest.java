package com.example.afternoon.strategy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withUserConfiguration(CardGateway.class, SepaGateway.class, Checkout.class);

    @Test
    void contextStarts() {
        runner.run(context -> assertThat(context).hasNotFailed());
    }

    @Test
    void checkoutUsesTheSepaGateway() {
        runner.run(context -> {
            Checkout checkout = context.getBean(Checkout.class);

            assertThat(checkout.pay(5.0)).isEqualTo("sepa:5.0");
        });
    }
}
