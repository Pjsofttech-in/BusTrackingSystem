package com.app.bustracking.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;

public record StudentFeePaymentRequest(
        @NotNull Long studentId,
        @NotNull @Positive Double amount,
        @NotBlank String paymentMode,
        String transactionId,
        String status   // optional, defaults to "SUCCESS"
) {}