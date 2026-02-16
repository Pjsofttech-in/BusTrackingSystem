package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.BusStop;
import com.bus.tracking.bus_tracking_system.repository.BusStopRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BusStopService {

    private final BusStopRepository repo;

    public BusStopService(BusStopRepository repo) {
        this.repo = repo;
    }

    // Add a new stop
    public BusStop addStop(BusStop stop) {
        return repo.save(stop);
    }

    // Get all stops of a bus in order
    public List<BusStop> getStopsByBus(Long busId) {
        return repo.findByBusIdOrderBySequenceNumberAsc(busId);
    }

    // Mark stop as reached
    public BusStop markStopReached(Long stopId) {
        Optional<BusStop> optionalStop = repo.findById(stopId);
        if (optionalStop.isPresent()) {
            BusStop stop = optionalStop.get();
            stop.setReached(true);
            stop.setReachedAt(LocalDateTime.now());
            return repo.save(stop);
        }
        return null;
    }

    // Get number of stops reached
    public long countStopsReached(Long busId) {
        return repo.findByBusIdOrderBySequenceNumberAsc(busId)
                .stream()
                .filter(BusStop::isReached)
                .count();
    }

    // BusStopService.java
    public void deleteStop(Long id) {
        repo.deleteById(id);
    }
}
