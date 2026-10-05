package com.example.afternoon.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * Exercise 9 (solved).
 *
 * - @ConditionalOnProperty on the class: the whole configuration only applies unless
 *   notifier.enabled=false (matchIfMissing = true means "on by default").
 * - @ConditionalOnMissingBean on the bean: back off when the user defined a Notifier.
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "notifier", name = "enabled", havingValue = "true", matchIfMissing = true)
public class NotifierAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Notifier.class)
    public Notifier notifier() {
        return new ConsoleNotifier();
    }
}
