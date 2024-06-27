package architecture.product.dtos;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class ProductDto {
    private UUID id;
    private String description;
    private BigDecimal price;
}
