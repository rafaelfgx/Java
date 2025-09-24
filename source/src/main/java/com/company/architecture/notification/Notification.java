package com.company.architecture.notification;

import jakarta.validation.constraints.NotBlank;

public record Notification(@NotBlank String message) {
}
