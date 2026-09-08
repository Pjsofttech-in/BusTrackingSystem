package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class BusRouteRequestDTO {
    private String routeName;
    private String description;
    private Long startStopId;
    private Long endStopId;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double totalDistanceKm;
    private Integer estimatedTimeMin;
    private String status;
    // ❌ Removed: busId, driverId, conductorId
    private List<Long> stopIds;   // ordered list of stop IDs
}