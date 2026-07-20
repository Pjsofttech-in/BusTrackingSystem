package com.app.bustracking.Request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record BusTripRequest(
        @NotNull Long busId,
        @NotNull Long routeId,
        Long driverId,
        Long conductorId,
        @NotNull LocalDateTime startTime,
        LocalDateTime endTime,
        @NotNull String tripStatus
) {}