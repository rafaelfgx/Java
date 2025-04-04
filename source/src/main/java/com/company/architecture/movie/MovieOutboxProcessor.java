package com.company.architecture.movie;

import com.company.architecture.shared.services.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieOutboxProcessor {
    private final MapperService mapperService;
    private final MovieOutboxRepository movieOutboxRepository;
    private final KafkaTemplate<String, Movie> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void process() {
        movieOutboxRepository.findAll().forEach(movieOutbox ->
            kafkaTemplate
                .send("movies", mapperService.fromJson(movieOutbox.getMovie(), Movie.class))
                .whenComplete((result, exception) -> movieOutboxRepository.delete(movieOutbox)));
    }
}
