package com.company.architecture.invoice.entities;

import com.company.architecture.invoice.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(indexes = {@Index(columnList = "number")}, uniqueConstraints = {@UniqueConstraint(columnNames = "number")})
public class Invoice extends BaseEntity {
    @NotNull
    @Size(min = 1, max = 50)
    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 50)
    private String number;

    @NotNull
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime dateTime = LocalDateTime.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvoiceStatus status;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @Builder.Default
    private Set<InvoiceItem> items = new HashSet<>();

    public Invoice addItem(final InvoiceItem item) {
        if (item == null) return this;
        if (items == null) items = new HashSet<>();
        item.setInvoice(this);
        items.add(item);
        return this;
    }

    public Invoice setItems(Set<InvoiceItem> items) {
        this.items = items != null ? items : new HashSet<>();
        this.items.forEach(item -> item.setInvoice(this));
        return this;
    }
}
