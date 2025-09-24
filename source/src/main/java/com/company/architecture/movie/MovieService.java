package com.company.architecture.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieOutboxRepository movieOutboxRepository;

    @Transactional
    public void add(Movie movie) {
        movieOutboxRepository.save(new MovieOutbox(movieRepository.save(movie)));
        log.info("[MovieService].[add]: {}", movie);
    }

    @KafkaListener(topics = MovieConfiguration.MOVIES, groupId = MovieConfiguration.MOVIES)
    public void listen(final Movie movie) {
        log.info("[MovieService].[listen]: {}", movie);
    }
}
