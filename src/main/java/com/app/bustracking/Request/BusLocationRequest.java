package com.app.bustracking.Request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record BusLocationRequest(
        @NotNull Long busId,
        @NotNull Double latitude,
        @NotNull Double longitude,
        Double speed,
        Double heading,
        Double accuracy,
        String status,
        LocalDateTime timestamp,
        Long directionId   // optional – if not provided, can be auto‑calculated from heading
) {}