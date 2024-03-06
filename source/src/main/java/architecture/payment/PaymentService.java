package architecture.payment;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final Map<String, PaymentStrategy> strategies;

    public String process(final PaymentMethod paymentMethod) {
        return strategies.get(paymentMethod.name()).pay();
    }
}
