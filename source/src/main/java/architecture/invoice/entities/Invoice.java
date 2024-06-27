package architecture.invoice.entities;

import architecture.invoice.InvoiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Invoice extends BaseEntity {
    @NotNull
    @Size(min = 1, max = 50)
    @EqualsAndHashCode.Include
    private String number;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<InvoiceItem> items;
}
