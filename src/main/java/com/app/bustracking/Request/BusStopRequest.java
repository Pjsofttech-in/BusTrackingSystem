// src/main/java/com/app/bustracking/request/BusStopRequest.java
package com.app.bustracking.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BusStopRequest(
        @NotBlank String stopName,
        @NotNull double latitude,
        @NotNull double longitude

) {}