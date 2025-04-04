package com.company.architecture.movie;

import jakarta.validation.constraints.NotBlank;

public record AddMovieDto(@NotBlank String title) {
}
