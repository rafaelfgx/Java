package com.company.architecture.payment;

import org.springframework.stereotype.Service;

@Service
public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentMethod paymentMethod() {
        return PaymentMethod.CREDIT_CARD;
    }

    @Override
    public String pay() {
        return CreditCardPaymentStrategy.class.getSimpleName();
    }
}
