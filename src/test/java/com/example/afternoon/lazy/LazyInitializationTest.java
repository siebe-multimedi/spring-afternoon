package com.example.afternoon.lazy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class LazyInitializationTest {

    private final ApplicationContextRunner runner =
            new ApplicationContextRunner().withUserConfiguration(HeavyService.class);

    @BeforeEach
    void reset() {
        HeavyService.CREATED.set(0);
    }

    @Test
    void heavyServiceIsNotCreatedAtStartup() {
        runner.run(context -> assertThat(HeavyService.CREATED.get()).isZero());
    }

    @Test
    void heavyServiceIsCreatedOnFirstUse() {
        runner.run(context -> {
            context.getBean(HeavyService.class).work();
            assertThat(HeavyService.CREATED.get()).isEqualTo(1);
        });
    }
}
