package architecture.invoice.dtos;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class InvoiceItemDto {
    private Long id;
    private String product;
    private Integer quantity;
    private BigDecimal unitPrice;
}
