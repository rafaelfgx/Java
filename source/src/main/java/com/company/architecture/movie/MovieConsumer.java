package com.company.architecture.movie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MovieConsumer {
    @KafkaListener(topics = "movies", groupId = "movie")
    public void consume(final Movie movie) {
        log.info("[MovieConsumer.consume]: {}", movie);
    }
}
