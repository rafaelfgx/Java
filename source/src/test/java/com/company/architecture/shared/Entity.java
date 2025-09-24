package com.company.architecture.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Entity {
    private UUID uuid;
    private String string;
    private BigDecimal bigDecimal;
    private LocalDate date;
    private Color color;
}
