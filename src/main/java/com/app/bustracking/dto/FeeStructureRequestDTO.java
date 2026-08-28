package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class FeeStructureRequestDTO {
    private Long routeId;
    private Long academicYearId;
    // amount removed
    private BigDecimal amountPerMonth;
    private BigDecimal amountPerYear;
    private BigDecimal amountPerKm;
    private LocalDate dueDate;
}