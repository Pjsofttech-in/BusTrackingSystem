// src/main/java/com/app/bustracking/model/BusStopModel.java
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
@Table(name = "busstop")
public class BusStopModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    BusModel bus;                 // ✅ correct relation

    String stopName;
    double latitude;
    double longitude;
    boolean reached = false;
    LocalDateTime reachedAt;
}