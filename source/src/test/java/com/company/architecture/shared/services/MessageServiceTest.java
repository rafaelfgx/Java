package com.company.architecture.shared.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootTest(classes = {ResourceBundleMessageSource.class, MessageService.class})
class MessageServiceTest {
    @Test
    void shouldReturnExpectedMessageWhenGettingMessageByKey() {
        Assertions.assertEquals("Test", MessageService.get("Test"));
    }
}
