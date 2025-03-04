package com.company.architecture.invoice.entities;

import com.company.architecture.invoice.InvoiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String number;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @Builder.Default
    private List<InvoiceItem> items = new ArrayList<>();
}
