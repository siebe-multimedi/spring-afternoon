package com.example.afternoon.cycle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class CircularDependencyTest {

    // PendingOrders was added to this list when it was extracted.
    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withUserConfiguration(OrderProcessor.class, InventoryChecker.class, PendingOrders.class);

    @Test
    void contextStarts() {
        runner.run(context -> assertThat(context).hasNotFailed());
    }

    @Test
    void anOrderReducesTheAvailableStock() {
        runner.run(context -> {
            OrderProcessor orders = context.getBean(OrderProcessor.class);
            InventoryChecker inventory = context.getBean(InventoryChecker.class);

            assertThat(orders.place("book", 4)).isTrue();
            assertThat(inventory.available("book")).isEqualTo(6);
        });
    }

    @Test
    void anOrderForMoreThanTheStockIsRefused() {
        runner.run(context -> {
            OrderProcessor orders = context.getBean(OrderProcessor.class);

            assertThat(orders.place("book", 4)).isTrue();
            assertThat(orders.place("book", 7)).isFalse();
        });
    }
}
