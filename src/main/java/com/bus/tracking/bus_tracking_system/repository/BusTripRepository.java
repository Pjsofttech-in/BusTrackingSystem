package com.bus.tracking.bus_tracking_system.repository;


import com.bus.tracking.bus_tracking_system.model.BusTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusTripRepository extends JpaRepository<BusTrip, Long> {
}
