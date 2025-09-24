package com.company.architecture.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UpdateUserDto(
    @Schema(hidden = true) UUID id,
    @NotBlank String name,
    @NotBlank @Email String email,
    @NotBlank String username,
    @NotBlank String password) {

    public UpdateUserDto withId(UUID id) {
        return new UpdateUserDto(id, name, email, username, password);
    }
}
