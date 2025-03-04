package com.company.architecture.shared.mediator;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class Mediator {
    private final Map<String, Handler<?, ?>> handlers;

    public Mediator(ApplicationContext context) {
        this.handlers = Map.ofEntries(context.getBeansOfType(Handler.class).values().stream().map(handler -> {
            final var args = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments();
            return Map.entry(args[0].getTypeName() + "_" + args[1].getTypeName(), handler);
        }).toArray(Map.Entry[]::new));
    }

    public <Request> Response<Void> handle(Request request) {
        return handle(request, Void.class);
    }

    public <Request, Body> Response<Body> handle(Request request, Class<Body> response) {
        final var violations = Validation.buildDefaultValidatorFactory().getValidator().validate(request);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return ((Handler<Request, Body>) handlers.get(request.getClass().getTypeName() + "_" + response.getTypeName())).handle(request);
    }
}
