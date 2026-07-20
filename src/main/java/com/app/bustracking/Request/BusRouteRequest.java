package com.app.bustracking.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalTime;
import java.util.List;

public record BusRouteRequest(
        @NotBlank String routeName,
        String description,

        Long startStopId,
        Long endStopId,
        LocalTime startTime,
        LocalTime endTime,
        Double totalDistanceKm,
        Integer estimatedTimeMin,
        String status,

        Long busId,
        Long driverId,
        Long conductorId,

        @NotEmpty(message = "At least one stop is required")
        List<Long> stopIds
) {}