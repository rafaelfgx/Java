package com.company.architecture.game;

import jakarta.validation.constraints.NotBlank;

public record Game(@NotBlank String title) {
}
