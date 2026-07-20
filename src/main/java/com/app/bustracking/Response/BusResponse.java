package com.app.bustracking.Response;

import java.time.LocalDateTime;

public record BusResponse(
        Long id,
        String busNumber,
        String busType,
        String busModelName,
        int mfgYear,
        int capacity,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long serviceProviderId,           // provider's ID
        String serviceProviderName        // provider's company name
) {}