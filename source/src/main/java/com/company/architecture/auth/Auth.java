package com.company.architecture.auth;

import jakarta.validation.constraints.NotBlank;

public record Auth(@NotBlank String username, @NotBlank String password) {
}
