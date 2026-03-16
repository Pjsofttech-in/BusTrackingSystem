package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;
    private String startTime;
    private String stopTime;
    private String startPoint;
    private String endPoint;
    private double totalDistanceKm;
    private int estimatedTimeMin;
    private String status;
    private String busStop;
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
}


