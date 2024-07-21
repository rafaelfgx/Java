package com.company.architecture.notification;

import com.company.architecture.KafkaTest;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;

@SpringBootTest(classes = {NotificationProducer.class})
@Import(NotificationTest.Configuration.class)
class NotificationTest extends KafkaTest {
    @Autowired
    NotificationProducer notificationProducer;

    @Test
    void testProduce() {
        notificationProducer.produce();
    }

    static class Configuration {
        @Bean
        public ProducerFactory<String, Notification> notificationProducerFactory() {
            final var configs = new HashMap<String, Object>();
            configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configs);
        }

        @Bean
        public KafkaTemplate<String, Notification> notificationKafkaTemplate() {
            return new KafkaTemplate<>(notificationProducerFactory());
        }
    }
}
