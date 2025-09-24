package com.company.architecture.category;

import jakarta.validation.constraints.NotBlank;

public record Category(@NotBlank String name) {
}
