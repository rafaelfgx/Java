package com.company.architecture.car;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void add(Car car) {
        log.info("[CarService].[add]: {}", car);
        applicationEventPublisher.publishEvent(car);
    }

    @EventListener
    public void listen(Car car) {
        log.info("[CarService].[listen]: {}", car);
    }
}
