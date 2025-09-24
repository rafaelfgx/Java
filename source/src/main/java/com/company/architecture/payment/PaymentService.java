package com.company.architecture.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final List<PaymentStrategy> strategies;

    public String process(final PaymentMethod paymentMethod) {
        return strategies.stream().filter(strategy -> strategy.paymentMethod().equals(paymentMethod)).findFirst().orElseThrow().pay();
    }
}
