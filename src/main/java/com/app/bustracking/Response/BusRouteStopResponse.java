package com.app.bustracking.Response;

public record BusRouteStopResponse(
        Long stopId,
        String stopName,
        double latitude,
        double longitude,
        int sequence
) {}