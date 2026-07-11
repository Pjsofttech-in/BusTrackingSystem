package com.bus.tracking.bus_tracking_system.repository;

import com.bus.tracking.bus_tracking_system.model.BusSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusSupplierRepository extends JpaRepository<BusSupplier, Long> {
    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);
}
