package com.example.afternoon.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MailPropertiesTest {

    @Autowired
    private MailProperties mail;

    @Test
    void hostAndPortAreBoundFromYaml() {
        assertThat(mail.getHost()).isEqualTo("smtp.example.org");
        assertThat(mail.getPort()).isEqualTo(587);
    }
}
