package com.company.architecture.shared.configurations;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Slf4j
@RestControllerAdvice
public class ExceptionConfiguration {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Void> handle(final AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handle(final ConstraintViolationException exception) {
        final var error = exception.getConstraintViolations().stream()
            .map(violation -> "%s: %s".formatted(violation.getPropertyPath(), violation.getMessage())).toList().toString();

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handle(final HttpMessageNotReadableException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(final MethodArgumentNotValidException exception) {
        final var fieldErrors = exception.getBindingResult().getFieldErrors().stream()
            .map(error -> "%s: %s".formatted(error.getField(), "typeMismatch".equals(error.getCode()) ? "must be valid" : error.getDefaultMessage()));

        final var globalErrors = exception.getBindingResult().getGlobalErrors().stream()
            .map(error -> "%s: %s".formatted(error.getObjectName(), error.getDefaultMessage()));

        final var errors = Stream.concat(fieldErrors, globalErrors).sorted().toList();

        return ResponseEntity.badRequest().body(errors.toString());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handle(final MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[%s: must be valid]".formatted(exception.getPropertyName()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handle(final NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handle(final ResponseStatusException exception) {
        if (isNull(exception.getCause())) {
            return ResponseEntity.status(HttpStatus.valueOf(exception.getStatusCode().value())).body(exception.getReason());
        }

        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.valueOf(exception.getStatusCode().value())).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handle(final Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
