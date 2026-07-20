package com.app.bustracking.Request;

import jakarta.validation.constraints.NotBlank;

public record StudentRequest(
        @NotBlank String name,
        @NotBlank String rollNumber,
        String admission,
        Boolean present,
        String status,
        Boolean inBus,
        String qrImageUrl,
        String parentName,
        String parentPhone,
        String parentEmail,
        String bloodGroup,
        Integer age,
        @NotBlank String studentClass,
        String division,
        String medium,
        String academicYear
) {}