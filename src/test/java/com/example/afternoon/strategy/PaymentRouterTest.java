package com.example.afternoon.strategy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentRouterTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withUserConfiguration(CardGateway.class, SepaGateway.class, PaymentRouter.class);

    @Test
    void routesToTheGatewayWithThatBeanName() {
        runner.run(context -> {
            PaymentRouter router = context.getBean(PaymentRouter.class);

            assertThat(router.pay("sepaGateway", 25.0)).isEqualTo("sepa:25.0");
            assertThat(router.pay("cardGateway", 25.0)).isEqualTo("card:25.0");
        });
    }

    @Test
    void anUnknownGatewayIsRejected() {
        runner.run(context -> {
            PaymentRouter router = context.getBean(PaymentRouter.class);

            assertThatThrownBy(() -> router.pay("cashGateway", 1.0))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void gatewaysAreInjectedInOrderOfTheOrderAnnotation() {
        runner.run(context -> {
            PaymentRouter router = context.getBean(PaymentRouter.class);

            assertThat(router.gatewayOrder()).isEqualTo(List.of("sepa", "card"));
        });
    }
}
