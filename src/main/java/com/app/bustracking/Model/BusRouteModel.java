package com.app.bustracking.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "bus_route")
public class BusRouteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String routeName;
    String description;

    // ----- NEW FIELDS -----
    @ManyToOne
    @JoinColumn(name = "start_stop_id")
    BusStopModel startStop;

    @ManyToOne
    @JoinColumn(name = "end_stop_id")
    BusStopModel endStop;

    LocalTime startTime;
    LocalTime endTime;

    Double totalDistanceKm;
    Integer estimatedTimeMin;
    String status;

    // ----- Existing relations -----
    @ManyToOne
    @JoinColumn(name = "bus_id")
    BusModel bus;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    DriverModel driver;

    @ManyToOne
    @JoinColumn(name = "conductor_id")
    ConductorModel conductor;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence ASC")
    List<BusRouteStopModel> stops = new ArrayList<>();

    LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}