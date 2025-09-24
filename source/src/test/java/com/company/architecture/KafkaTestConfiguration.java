package com.company.architecture;

import com.company.architecture.movie.Movie;
import com.company.architecture.notification.Notification;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.testcontainers.kafka.KafkaContainer;

import java.util.HashMap;

@TestConfiguration(proxyBeanMethods = false)
public class KafkaTestConfiguration {
    @Bean
    @ServiceConnection
    KafkaContainer kafkaContainer() {
        return new KafkaContainer("apache/kafka-native");
    }

    @Bean
    public KafkaTemplate<String, Notification> kafkaTemplateNotification(KafkaContainer container) {
        return kafkaTemplate(container, Notification.class);
    }

    @Bean
    public KafkaTemplate<String, Movie> kafkaTemplateMovie(KafkaContainer container) {
        return kafkaTemplate(container, Movie.class);
    }

    private <T> KafkaTemplate<String, T> kafkaTemplate(KafkaContainer container, Class<T> valueType) {
        final var configs = new HashMap<String, Object>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, container.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        var factory = new DefaultKafkaProducerFactory<String, T>(configs);
        return new KafkaTemplate<>(factory);
    }
}
