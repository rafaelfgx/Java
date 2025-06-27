package com.company.architecture.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {PaymentService.class, CreditCardPaymentStrategy.class, DebitCardPaymentStrategy.class})
class PaymentServiceTest {
    @Autowired
    PaymentService paymentService;

    @Test
    void shouldReturnCreditCard() {
        Assertions.assertEquals(CreditCardPaymentStrategy.class.getSimpleName(), paymentService.process(PaymentMethod.CREDIT_CARD));
    }

    @Test
    void shouldReturnDebitCard() {
        Assertions.assertEquals(DebitCardPaymentStrategy.class.getSimpleName(), paymentService.process(PaymentMethod.DEBIT_CARD));
    }
}
