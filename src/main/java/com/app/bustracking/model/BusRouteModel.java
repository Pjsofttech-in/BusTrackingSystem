package com.app.bustracking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bus_route")
public class BusRouteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "start_stop_id")
    private BusStopModel startStop;

    @ManyToOne
    @JoinColumn(name = "end_stop_id")
    private BusStopModel endStop;

    private LocalTime startTime;
    private LocalTime endTime;
    private Double totalDistanceKm;
    private Integer estimatedTimeMin;
    private String status;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private BusModel bus;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverModel driver;

    @ManyToOne
    @JoinColumn(name = "conductor_id")
    private ConductorModel conductor;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence ASC")
    private List<BusRouteStopModel> stops = new ArrayList<>();

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}