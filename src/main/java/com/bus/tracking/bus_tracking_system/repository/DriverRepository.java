package com.bus.tracking.bus_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bus.tracking.bus_tracking_system.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    // 🔐 For Login
    Driver findByPhone(String phone);
}
