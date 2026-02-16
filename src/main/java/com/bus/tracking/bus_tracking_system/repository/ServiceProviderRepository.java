package com.bus.tracking.bus_tracking_system.repository;

import com.bus.tracking.bus_tracking_system.model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProviderRepository
        extends JpaRepository<ServiceProvider, Long> {
}
