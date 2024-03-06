package architecture.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { PaymentService.class, CreditCardPaymentStrategy.class, DebitCardPaymentStrategy.class })
class PaymentServiceTest {
    @Autowired
    PaymentService paymentService;

    @Test
    void testProcessCreditCard() {
        Assertions.assertEquals(PaymentMethod.CREDIT_CARD.name(), paymentService.process(PaymentMethod.CREDIT_CARD));
    }

    @Test
    void testProcessDebitCard() {
        Assertions.assertEquals(PaymentMethod.DEBIT_CARD.name(), paymentService.process(PaymentMethod.DEBIT_CARD));
    }
}
