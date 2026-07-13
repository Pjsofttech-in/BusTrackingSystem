package com.bus.tracking.bus_tracking_system.model;

import com.bus.tracking.bus_tracking_system.model.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship with ServiceProvider
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_provider_id")
    private ServiceProvider serviceProvider;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = true)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusLocation> locations;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusStop> stops;

    @Column(name = "bus_number", unique = true, nullable = false)
    private String busNumber;  // Common field - matches ServiceProvider.busNumber

    @Column(name = "bus_type")
    private String busType;

    @Column(name = "mfg_year")
    private int mfgYear;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "status")
    private String status;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}