package com.company.architecture.shared.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ValidatorService {
    private static boolean cpfVerifier(CharSequence value, int length) {
        final var verifier = IntStream.range(0, length).map(i -> Character.getNumericValue(value.charAt(i)) * (length + 1 - i)).sum() * 10 % 11 % 10;
        return verifier == Character.getNumericValue(value.charAt(length));
    }

    private static boolean cnpjVerifier(CharSequence value, int index) {
        final var weights = new ArrayList<>(List.of(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2));
        if (index == 12) weights.removeFirst();
        final var sum = IntStream.range(0, index).map(i -> Character.getNumericValue(value.charAt(i)) * weights.get(i)).sum();
        final var verifier = ((sum % 11) < 2) ? 0 : (11 - (sum % 11));
        return verifier == Character.getNumericValue(value.charAt(index));
    }

    public List<ConstraintViolation<Object>> validate(Object object) {
        try (final var validatorFactory = Validation.buildDefaultValidatorFactory()) {
            return validatorFactory.getValidator().validate(object).stream().toList();
        }
    }

    public boolean validateCpf(String value) {
        return (value = value.replaceAll("\\D", "")).matches("\\d{11}") && !value.matches("(\\d)\\1{10}") && cpfVerifier(value, 9) && cpfVerifier(value, 10);
    }

    public boolean validateCnpj(String value) {
        return (value = value.replaceAll("\\D", "")).matches("\\d{14}") && !value.matches("(\\d)\\1{13}") && cnpjVerifier(value, 12) && cnpjVerifier(value, 13);
    }
}
