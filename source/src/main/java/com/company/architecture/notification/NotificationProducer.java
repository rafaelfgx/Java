package com.company.architecture.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<String, Notification> kafkaTemplate;

    public void produce() {
        kafkaTemplate.send("notifications", new Notification(LocalDateTime.now().toString()));
    }
}
