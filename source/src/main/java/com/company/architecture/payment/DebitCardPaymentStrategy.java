package com.company.architecture.payment;

import org.springframework.stereotype.Service;

@Service
public class DebitCardPaymentStrategy implements PaymentStrategy {
    @Override
    public String pay() {
        return DebitCardPaymentStrategy.class.getSimpleName();
    }
}