package com.company.architecture.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final Map<String, PaymentStrategy> strategies;

    public String process(final PaymentMethod paymentMethod) {
        return strategies.get(paymentMethod.name()).pay();
    }
}
