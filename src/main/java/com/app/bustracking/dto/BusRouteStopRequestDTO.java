package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusRouteStopRequestDTO {
    private Long routeId;
    private Long stopId;
    private Integer sequence;
}