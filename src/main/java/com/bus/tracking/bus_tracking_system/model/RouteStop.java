package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stopName;
    private String arrivalTime;
    private Integer stopOrder;
    private String status;

    private Double latitude;
    private Double longitude;

    //  Many Stops → One Route
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
}