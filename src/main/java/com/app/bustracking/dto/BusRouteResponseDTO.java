package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class BusRouteResponseDTO {
    private Long id;
    private String routeName;
    private String description;

    // Start/End stop details
    private Long startStopId;
    private String startStopName;
    private Long endStopId;
    private String endStopName;

    private LocalTime startTime;
    private LocalTime endTime;
    private Double totalDistanceKm;
    private Integer estimatedTimeMin;
    private String status;

    // ❌ Removed: busId, busNumber, driverId, driverName, conductorId, conductorName

    private List<BusRouteStopResponseDTO> stops;
    private LocalDateTime createdAt;
}