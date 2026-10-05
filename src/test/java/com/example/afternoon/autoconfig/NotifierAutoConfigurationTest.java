package com.example.afternoon.autoconfig;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class NotifierAutoConfigurationTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(NotifierAutoConfiguration.class));

    @Test
    void createsADefaultNotifierWhenNothingElseIsDefined() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(Notifier.class);
            assertThat(context.getBean(Notifier.class).send("hi")).isEqualTo("console: hi");
        });
    }

    @Test
    void backsOffWhenTheUserDefinesTheirOwnNotifier() {
        runner.withUserConfiguration(CustomNotifierConfig.class).run(context -> {
            assertThat(context).hasSingleBean(Notifier.class);
            assertThat(context.getBean(Notifier.class).send("hi")).isEqualTo("custom: hi");
        });
    }

    @Test
    void canBeSwitchedOffWithAProperty() {
        runner.withPropertyValues("notifier.enabled=false").run(context ->
                assertThat(context).doesNotHaveBean(Notifier.class));
    }

    @TestConfiguration(proxyBeanMethods = false)
    static class CustomNotifierConfig {

        @Bean
        Notifier customNotifier() {
            return message -> "custom: " + message;
        }
    }
}
