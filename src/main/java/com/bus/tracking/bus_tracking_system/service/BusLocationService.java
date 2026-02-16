package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.BusLocation;
import com.bus.tracking.bus_tracking_system.repository.BusLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusLocationService {

    private final BusLocationRepository repo;

    public BusLocationService(BusLocationRepository repo) {
        this.repo = repo;
    }

    public BusLocation updateLocation(BusLocation location) {
        return repo.save(location);
    }

    public BusLocation getLatestLocation(Long busId) {
        return repo.findTopByBusIdOrderByRecordedAtDesc(busId);
    }

    public List<BusLocation> getLocationHistory(Long busId) {
        return repo.findByBusIdOrderByRecordedAtAsc(busId);
    }
}
