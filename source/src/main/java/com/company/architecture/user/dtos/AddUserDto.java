package com.company.architecture.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AddUserDto(
    @NotBlank String name,
    @NotBlank @Email String email,
    @NotBlank String username,
    @NotBlank String password) {
}
