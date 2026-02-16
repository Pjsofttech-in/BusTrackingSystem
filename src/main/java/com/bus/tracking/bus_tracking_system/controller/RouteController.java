package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.Route;
import com.bus.tracking.bus_tracking_system.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    private final RouteService service;

    public RouteController(RouteService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Route addRoute(@RequestBody Route route) {
        return service.addRoute(route);
    }

    @GetMapping("/all")
    public List<Route> getAllRoutes() {
        return service.getAllRoutes();
    }

    @GetMapping("/{id}")
    public Route getRoute(@PathVariable Long id) {
        return service.getRouteById(id);
    }
    @PutMapping("/update/{id}")
    public Route updateRoute(@PathVariable Long id, @RequestBody Route updated) {
        Route existing = service.getRouteById(id);
        if (existing == null) return null;

        existing.setRouteName(updated.getRouteName());
        existing.setStartPoint(updated.getStartPoint());
        existing.setEndPoint(updated.getEndPoint());
        existing.setTotalDistanceKm(updated.getTotalDistanceKm());
        existing.setEstimatedTimeMin(updated.getEstimatedTimeMin());
        existing.setStatus(updated.getStatus());

        return service.addRoute(existing);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Long id) {
        service.deleteRoute(id);
        return "Route deleted";
    }
}
