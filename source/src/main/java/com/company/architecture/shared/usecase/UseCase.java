package com.company.architecture.shared.usecase;

public interface UseCase<I extends Input, O extends Output> {
    O execute(I input);
}
