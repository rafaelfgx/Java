package architecture.invoice.dtos;

import architecture.invoice.InvoiceStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class AddInvoiceDto {
    @NotNull
    @Size(min = 1, max = 50)
    private String number;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private InvoiceStatus status;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<AddInvoiceItemDto> items;
}
