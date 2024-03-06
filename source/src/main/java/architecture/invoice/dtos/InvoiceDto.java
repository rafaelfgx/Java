package architecture.invoice.dtos;

import architecture.invoice.InvoiceStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class InvoiceDto {
    private Long id;
    private String number;
    private LocalDateTime dateTime;
    private InvoiceStatus status;
    private List<InvoiceItemDto> items;
}
