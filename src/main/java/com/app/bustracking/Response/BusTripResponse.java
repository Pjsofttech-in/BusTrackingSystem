package com.app.bustracking.Response;

import java.time.LocalDateTime;

public record BusTripResponse(
        Long id,
        Long busId,
        String busNumber,
        Long routeId,
        String routeName,
        Long driverId,
        String driverName,
        Long conductorId,
        String conductorName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String tripStatus,
        LocalDateTime createdAt
) {}