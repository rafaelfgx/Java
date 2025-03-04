package com.company.architecture.invoice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class InvoiceItem extends BaseEntity {
    @NotNull
    @Size(min = 1, max = 100)
    private String product;

    @NotNull
    @Min(value = 1)
    private BigDecimal quantity;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;
}
