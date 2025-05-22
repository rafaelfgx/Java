package com.company.architecture.animal;

import com.company.architecture.shared.usecase.UseCase;

import java.util.UUID;

public class CreateAnimalUseCase implements UseCase<CreateAnimalInput, CreateAnimalOutput> {
    @Override
    public CreateAnimalOutput execute(CreateAnimalInput input) {
        return new CreateAnimalOutput(UUID.randomUUID());
    }
}
