package com.company.architecture.movie;

import com.company.architecture.shared.services.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MapperService mapperService;
    private final MovieRepository movieRepository;
    private final MovieOutboxRepository movieOutboxRepository;

    @Transactional
    public void add(AddMovieDto dto) {
        final var movie = movieRepository.save(new Movie(0, dto.title()));
        movieOutboxRepository.save(new MovieOutbox(UUID.randomUUID(), mapperService.toJson(movie)));
    }
}
