package com.company.architecture.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {
    @KafkaListener(topics = "notifications", groupId = "group")
    public void listen(final Notification notification) {
        log.info("[NOTIFICATION_CONSUMER].[LISTEN]: {}.", notification);
    }
}
