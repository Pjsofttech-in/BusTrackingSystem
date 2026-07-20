package com.app.bustracking.Response;

import java.time.LocalDateTime;

public record StudentScanResponse(
        Long id,
        Long studentId,
        String studentName,
        String studentRollNumber,
        Long busId,
        String busNumber,
        LocalDateTime scannedAt
) {}