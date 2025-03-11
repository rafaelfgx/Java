package com.company.architecture.movie;

import com.company.architecture.shared.swagger.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Movies")
@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @Operation(summary = "Add")
    @PostApiResponses
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody @Valid final AddMovieDto dto) {
        movieService.add(dto);
    }
}
