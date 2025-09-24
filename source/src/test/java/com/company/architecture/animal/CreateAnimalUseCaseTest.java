package com.company.architecture.animal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class CreateAnimalUseCaseTest {
    @Test
    void shouldReturnValidOutputWithNonNullId() {
        final var useCase = new CreateAnimalUseCase();
        final var input = new CreateAnimalInput("Animal");
        final var output = useCase.execute(input);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());
        Assertions.assertInstanceOf(UUID.class, output.id());
    }
}
