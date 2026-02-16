package com.bus.tracking.bus_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bus.tracking.bus_tracking_system.model.StudentScan;

public interface StudentScanRepository extends JpaRepository<StudentScan, Long> {

    long countByBusId(Long busId);
}
