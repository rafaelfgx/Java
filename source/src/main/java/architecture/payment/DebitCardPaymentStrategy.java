package architecture.payment;

import org.springframework.stereotype.Service;

@Service("DEBIT_CARD")
public class DebitCardPaymentStrategy implements PaymentStrategy {
    @Override
    public String pay() {
        return PaymentMethod.DEBIT_CARD.name();
    }
}
