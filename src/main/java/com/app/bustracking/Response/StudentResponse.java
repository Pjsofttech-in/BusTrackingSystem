package com.app.bustracking.Response;

public record StudentResponse(
        Long id,
        String name,
        String rollNumber,
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
        String studentClass,
        String division,
        String medium,
        String academicYear
) {}