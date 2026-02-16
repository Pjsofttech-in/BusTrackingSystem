package com.bus.tracking.bus_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bus.tracking.bus_tracking_system.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
