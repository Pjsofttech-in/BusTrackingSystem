package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusStopRequestDTO {
    private Long busId;          // relation
    private String stopName;
    private Double latitude;
    private Double longitude;
    private Boolean reached;     // optional, default false
}