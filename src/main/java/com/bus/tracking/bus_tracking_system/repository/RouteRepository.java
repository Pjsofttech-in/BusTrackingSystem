package com.bus.tracking.bus_tracking_system.repository;

import com.bus.tracking.bus_tracking_system.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}