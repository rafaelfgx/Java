package com.company.architecture.shared.mediator;

import jakarta.validation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@SuppressWarnings("unchecked")
public class Mediator {
    private record Key(Class<?> request, Class<?> response) {}
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private static final Map<Key, Handler<?, ?>> handlers = new ConcurrentHashMap<>();

    public Mediator(final ApplicationContext context) {
        context.getBeansOfType(Handler.class).values().forEach(handler -> {
            final var arguments = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments();
            handlers.put(new Key((Class<?>) arguments[0], (Class<?>) arguments[1]), handler);
        });
    }

    public <Request, Response> ResponseEntity<Response> handle(Request request, Class<Response> responseType) {
        final var violations = validator.validate(request);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
        return ((Handler<Request, Response>) handlers.get(new Key(request.getClass(), responseType))).handle(request);
    }
}
