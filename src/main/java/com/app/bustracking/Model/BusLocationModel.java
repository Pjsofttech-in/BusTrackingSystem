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
@Table(name = "bus_location")
public class BusLocationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    BusModel bus;

    @Column(nullable = false)
    Double latitude;

    @Column(nullable = false)
    Double longitude;

    Double speed;          // km/h
    Double heading;        // degrees (0-360)

    // NEW: reference to Direction entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direction_id")
    DirectionModel direction;

    Double accuracy;       // meters

    @Column(length = 50)
    String status;         // e.g., "ON_ROUTE", "STOPPED", "OFFLINE"

    LocalDateTime timestamp;   // when the location was captured

    LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (timestamp == null) timestamp = LocalDateTime.now();
    }
}