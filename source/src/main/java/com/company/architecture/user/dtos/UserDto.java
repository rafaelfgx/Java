package com.company.architecture.user.dtos;

import java.util.UUID;

public record UserDto(
    UUID id,
    String name,
    String email) {
}
