package com.company.architecture.car;

import jakarta.validation.constraints.NotBlank;

public record Car(@NotBlank String brand, @NotBlank String model) {
}
