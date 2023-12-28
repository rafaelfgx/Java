package architecture.invoice.entities;

import architecture.invoice.InvoiceStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @EqualsAndHashCode.Include
    private String number;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private InvoiceStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<InvoiceItem> items;
}
