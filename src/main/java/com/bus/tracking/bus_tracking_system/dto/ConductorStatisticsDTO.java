package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConductorStatisticsDTO {

    private long total;
    private long active;
    private long suspended;
    private long terminated;
    private long withEmail;
    private long expiredLicenses;
    private Map<String, Long> byCity;
    private Map<String, Long> byStatus;
}