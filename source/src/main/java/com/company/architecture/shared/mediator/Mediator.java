package com.company.architecture.shared.mediator;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import static java.util.stream.Collectors.toUnmodifiableMap;

@Service
@SuppressWarnings("unchecked")
public class Mediator {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final Map<String, Handler<?, ?>> handlers;

    public Mediator(ApplicationContext context) {
        this.handlers = context.getBeansOfType(Handler.class).values().stream().collect(toUnmodifiableMap(handler -> {
            final var args = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments();
            return ((Class<?>) args[0]).getSimpleName() + "_" + ((Class<?>) args[1]).getSimpleName();
        }, handler -> handler));
    }

    public <Request, Response> ResponseEntity<Response> handle(Request request, Class<Response> response) {
        final var violations = validator.validate(request);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
        final var id = request.getClass().getSimpleName() + "_" + response.getSimpleName();
        return ((Handler<Request, Response>) handlers.get(id)).handle(request);
    }
}
