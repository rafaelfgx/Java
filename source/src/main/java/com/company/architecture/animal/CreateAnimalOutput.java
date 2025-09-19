package com.company.architecture.animal;

import com.company.architecture.shared.usecase.Output;

import java.util.UUID;

public record CreateAnimalOutput(UUID id) implements Output {
}
