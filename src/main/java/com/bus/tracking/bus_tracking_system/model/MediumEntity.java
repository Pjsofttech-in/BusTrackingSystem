package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class MediumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mediumid")
    private Long id;
    private String mediumName;
}
