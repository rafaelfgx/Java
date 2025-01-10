package com.company.architecture.shared;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record Dto(UUID uuid, String string, BigDecimal bigDecimal, LocalDate date, Color color) {
}
