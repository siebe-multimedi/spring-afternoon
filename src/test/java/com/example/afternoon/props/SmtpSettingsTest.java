package com.example.afternoon.props;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class SmtpSettingsTest {

    private final ApplicationContextRunner runner =
            new ApplicationContextRunner().withUserConfiguration(MailConfig.class);

    @Test
    void bindsHostPortAndRecipients() {
        runner.withPropertyValues(
                "smtp.host=smtp.example.org",
                "smtp.port=2525",
                "smtp.recipients[0].name=Ann",
                "smtp.recipients[0].email=ann@example.org").run(context -> {
            SmtpSettings settings = context.getBean(SmtpSettings.class);

            assertThat(settings.host()).isEqualTo("smtp.example.org");
            assertThat(settings.port()).isEqualTo(2525);
            assertThat(settings.recipients().get(0).name()).isEqualTo("Ann");
            assertThat(settings.recipients().get(0).email()).isEqualTo("ann@example.org");
        });
    }

    @Test
    void retriesAndTimeoutHaveDefaults() {
        runner.withPropertyValues("smtp.host=smtp.example.org", "smtp.port=25").run(context -> {
            SmtpSettings settings = context.getBean(SmtpSettings.class);

            assertThat(settings.retries()).isEqualTo(3);
            assertThat(settings.timeout()).isEqualTo(Duration.ofSeconds(5));
        });
    }

    @Test
    void aMissingHostPreventsStartup() {
        runner.withPropertyValues("smtp.port=25").run(context ->
                assertThat(context).hasFailed());
    }

    @Test
    void anInvalidPortPreventsStartup() {
        runner.withPropertyValues("smtp.host=smtp.example.org", "smtp.port=70000").run(context ->
                assertThat(context).hasFailed());
    }

    @TestConfiguration(proxyBeanMethods = false)
    @EnableConfigurationProperties(SmtpSettings.class)
    static class MailConfig {
    }
}
