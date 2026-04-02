package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "https://pjsofttech.com")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    //  CREATE
    @PostMapping
    public RouteResponseDTO save(@RequestBody RouteRequestDTO dto) {
        return routeService.saveRoute(dto);
    }

    //  GET BY ID
    @GetMapping("/{id}")
    public RouteResponseDTO getById(@PathVariable Long id) {
        return routeService.getRoute(id);
    }

    //  GET ALL
    @GetMapping
    public List<RouteResponseDTO> getAll() {
        return routeService.getAllRoutes();
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return "Route deleted successfully";
    }
}