package com.app.bustracking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "busstop")
public class BusStopModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private BusModel bus;

    private String stopName;
    private double latitude;
    private double longitude;
    private boolean reached = false;
    private LocalDateTime reachedAt;
}