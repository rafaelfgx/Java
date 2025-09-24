package com.company.architecture.notification;

import com.company.architecture.KafkaTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest(classes = {NotificationService.class})
@Import(KafkaTestConfiguration.class)
class NotificationTest {
    @Autowired
    NotificationService notificationService;

    @Autowired
    KafkaTemplate<String, Notification> kafkaTemplate;

    @Test
    void shouldNotThrowExceptionWhenSendingNotification() {
        Assertions.assertDoesNotThrow(() -> notificationService.send(new Notification("Message")));
    }
}
