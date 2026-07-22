package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class BusTripRequestDTO {
    private Long busId;
    private Long routeId;
    private Long driverId;
    private Long conductorId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String tripStatus;
}