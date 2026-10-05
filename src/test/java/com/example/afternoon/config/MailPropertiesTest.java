package com.example.afternoon.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MailPropertiesTest {

    @Autowired
    private MailProperties mail;

    @Test
    void valuesAreBoundFromYaml() {
        assertThat(mail.getHost()).isEqualTo("smtp.example.org");
        assertThat(mail.getPort()).isEqualTo(587);
        assertThat(mail.getRetries()).isEqualTo(3);
        assertThat(mail.getConnectionTimeout()).isEqualTo(Duration.ofSeconds(5));
    }
}
