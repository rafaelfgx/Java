package com.company.architecture.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationConsumer {
    @KafkaListener(topics = "notifications", groupId = "notification")
    public void consume(final Notification notification) {
        log.info("[NotificationConsumer.consume]: {}", notification);
    }
}
