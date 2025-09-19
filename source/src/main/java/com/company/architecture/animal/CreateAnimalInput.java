package com.company.architecture.animal;

import com.company.architecture.shared.usecase.Input;

public record CreateAnimalInput(String name) implements Input {
}
