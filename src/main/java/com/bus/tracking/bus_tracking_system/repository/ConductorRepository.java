package com.bus.tracking.bus_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bus.tracking.bus_tracking_system.model.Conductor;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {
}
