package com.company.architecture.shared.mediator;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import static java.util.stream.Collectors.toUnmodifiableMap;

@Service
@SuppressWarnings("unchecked")
public class Mediator {
    private final Map<String, Handler<?, ?>> handlers;
    private final Validator validator;

    public Mediator(ApplicationContext context) {
        this.handlers = context.getBeansOfType(Handler.class).values().stream().collect(toUnmodifiableMap(handler -> {
            final var args = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments();
            return ((Class<?>) args[0]).getSimpleName() + "_" + ((Class<?>) args[1]).getSimpleName();
        }, handler -> handler));

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <Request> Response<Void> handle(Request request) {
        return handle(request, Void.class);
    }

    public <Request, Body> Response<Body> handle(Request request, Class<Body> response) {
        final var violations = validator.validate(request);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        final var id = request.getClass().getSimpleName() + "_" + response.getSimpleName();

        return ((Handler<Request, Body>) handlers.get(id)).handle(request);
    }
}
