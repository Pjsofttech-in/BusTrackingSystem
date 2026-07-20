package com.app.bustracking.Request;

public record BusRequest(
        String busNumber,
        String busType,
        String busModelName,
        int mfgYear,
        int capacity,
        String status,
        Long serviceProviderId            // new field
) {}