package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BusTripRequestDTO {

    private Long busId;
    private Long routeId;
    private Long driverId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String tripStatus;
}