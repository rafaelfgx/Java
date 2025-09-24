package com.company.architecture.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MovieOutboxService {
    private final MovieOutboxRepository movieOutboxRepository;
    private final KafkaTemplate<String, Movie> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void process() {
        movieOutboxRepository.findAll().forEach(movieOutbox -> {
            kafkaTemplate.send(MovieConfiguration.MOVIES, movieOutbox.getMovie()).whenComplete((_, _) -> movieOutboxRepository.delete(movieOutbox));
            log.info("[MovieOutboxService].[process]: {}", movieOutbox.getMovie());
        });
    }
}
