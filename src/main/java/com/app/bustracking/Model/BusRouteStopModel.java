package com.app.bustracking.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "bus_route_stop")
public class BusRouteStopModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    BusRouteModel route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_id", nullable = false)
    BusStopModel stop;

    int sequence;   // order of the stop in the route
}