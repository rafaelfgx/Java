package architecture.payment;

import org.springframework.stereotype.Service;

@Service("CREDIT_CARD")
public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public String pay() {
        return PaymentMethod.CREDIT_CARD.name();
    }
}
