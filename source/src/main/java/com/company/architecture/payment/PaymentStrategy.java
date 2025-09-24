package com.company.architecture.payment;

public interface PaymentStrategy {
    PaymentMethod paymentMethod();

    String pay();
}
