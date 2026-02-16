package com.bus.tracking.bus_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bus.tracking.bus_tracking_system.model.BusStop;
import java.util.List;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {

    List<BusStop> findByBusIdOrderBySequenceNumberAsc(Long busId);

}
