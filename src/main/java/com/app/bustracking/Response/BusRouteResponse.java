package com.app.bustracking.Response;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record BusRouteResponse(
        Long id,
        String routeName,
        String description,

        // New fields
        Long startStopId,
        String startStopName,
        Long endStopId,
        String endStopName,
        LocalTime startTime,
        LocalTime endTime,
        Double totalDistanceKm,
        Integer estimatedTimeMin,
        String status,

        // Existing
        Long busId,
        String busNumber,
        Long driverId,
        String driverName,
        Long conductorId,
        String conductorName,
        List<BusRouteStopResponse> stops,
        LocalDateTime createdAt
) {}