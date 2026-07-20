package com.app.bustracking.Response;

import java.time.LocalDateTime;

public record BusLocationResponse(
        Long id,
        Long busId,
        String busNumber,
        Double latitude,
        Double longitude,
        Double speed,
        Double heading,
        Long directionId,
        String directionName,   // fetched from DirectionModel.name
        Double accuracy,
        String status,
        LocalDateTime timestamp,
        LocalDateTime createdAt
) {}