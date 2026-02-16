package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.Route;
import com.bus.tracking.bus_tracking_system.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository repo;

    public RouteService(RouteRepository repo) {
        this.repo = repo;
    }

    public Route addRoute(Route route) {
        return repo.save(route);
    }

    public List<Route> getAllRoutes() {
        return repo.findAll();
    }

    public Route getRouteById(Long id) {
        return repo.findById(id).orElse(null);
    }
    // RouteService.java
    public void deleteRoute(Long id) {
        repo.deleteById(id);
    }
}
