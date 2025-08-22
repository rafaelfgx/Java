package com.company.architecture.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final KafkaTemplate<String, Notification> kafkaTemplate;
    private static final String NOTIFICATIONS = "notifications";

    public void send(Notification notification) {
        kafkaTemplate.send(NOTIFICATIONS, notification);
        log.info("[NotificationService].[send]: {}", notification);
    }

    @KafkaListener(topics = NOTIFICATIONS, groupId = NOTIFICATIONS)
    public void listen(final Notification notification) {
        log.info("[NotificationService].[listen]: {}", notification);
    }
}
