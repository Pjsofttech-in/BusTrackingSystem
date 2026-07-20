package com.app.bustracking.Response;

import java.util.List;
import java.util.Map;

public record DashboardResponse(
        String academicYear,
        List<Map<String, Object>> buses,
        List<Map<String, Object>> busLocations,
        List<String> busStops,
        List<String> conductors,
        List<String> drivers,
        List<String> divisions,
        List<String> mediums,
        List<Map<String, Object>> routes,
        List<Map<String, Object>> routeStops,
        List<String> serviceProviders,
        List<Map<String, Object>> students,
        List<Map<String, Object>> studentFeePayments,
        List<Map<String, Object>> studentScans,
        Map<String, Object> stats
) {}