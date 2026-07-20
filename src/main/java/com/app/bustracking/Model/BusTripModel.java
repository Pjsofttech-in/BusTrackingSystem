package com.app.bustracking.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "bus_trip")
public class BusTripModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    BusModel bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    BusRouteModel route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    DriverModel driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conductor_id")
    ConductorModel conductor;

    LocalDateTime startTime;
    LocalDateTime endTime;

    @Column(length = 50)
    String tripStatus;       // SCHEDULED, ONGOING, COMPLETED, CANCELLED

    LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}