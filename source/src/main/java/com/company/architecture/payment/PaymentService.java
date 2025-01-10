package com.company.architecture.payment;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class PaymentService {
    private final TreeMap<String, PaymentStrategy> strategies = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public PaymentService(final Map<String, PaymentStrategy> strategies) {
        this.strategies.putAll(strategies);
    }

    public String process(final PaymentMethod paymentMethod) {
        return strategies.get(paymentMethod.getStrategy()).pay();
    }
}
