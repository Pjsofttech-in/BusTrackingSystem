package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class StudentFeePaymentRequestDTO {
    private Long studentId;
    private Double amount;
    private String paymentMode;
    private String status;
    private LocalDate paymentDate;
    private LocalDate dueDate;
    private String transactionId;
}