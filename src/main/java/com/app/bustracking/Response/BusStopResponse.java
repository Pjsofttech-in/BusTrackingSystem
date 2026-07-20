// src/main/java/com/app/bustracking/response/BusStopResponse.java
package com.app.bustracking.Response;

import java.time.LocalDateTime;

public record BusStopResponse(
        Long id,
        String stopName,
        double latitude,
        double longitude,

//        String busNumber,      // convenience field
        boolean reached,
        LocalDateTime reachedAt
) {}