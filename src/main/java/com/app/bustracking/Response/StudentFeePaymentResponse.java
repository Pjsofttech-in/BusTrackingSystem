package com.app.bustracking.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentFeePaymentResponse(
        Long id,
        Long studentId,
        String studentName,
        Double amount,
        String paymentMode,
        String status,
        LocalDate paymentDate,
        LocalDateTime paymentDateTime,
        String transactionId
) {}