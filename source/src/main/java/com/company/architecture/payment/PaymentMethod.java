package com.company.architecture.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentMethod {
    CREDIT_CARD(CreditCardPaymentStrategy.class.getSimpleName()),
    DEBIT_CARD(DebitCardPaymentStrategy.class.getSimpleName());
    private final String strategy;
}
