package com.bus.tracking.bus_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bus.tracking.bus_tracking_system.model.BusLocation;

import java.util.List;

public interface BusLocationRepository extends JpaRepository<BusLocation, Long> {

    List<BusLocation> findByBusIdOrderByRecordedAtAsc(Long busId);

    BusLocation findTopByBusIdOrderByRecordedAtDesc(Long busId);
}
