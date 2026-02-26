package com.bus.tracking.bus_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bus.tracking.bus_tracking_system.model.BusLocation;

import java.util.List;

public interface BusLocationRepository extends JpaRepository<BusLocation, Long> {

    List<BusLocation> findByBus_IdOrderByRecordedAtAsc(Long busId);

    BusLocation findTopByBus_IdOrderByRecordedAtDesc(Long busId);
}
