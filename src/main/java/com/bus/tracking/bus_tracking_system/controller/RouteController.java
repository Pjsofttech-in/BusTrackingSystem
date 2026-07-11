package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
//@CrossOrigin(origins = "https://pjsofttech.com")
@CrossOrigin(origins = "http://localhost:5173")
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

    //UPDATE
    @PutMapping("/{id}")
    public RouteResponseDTO update(@PathVariable Long id,
                                   @RequestBody RouteRequestDTO dto) {
        return routeService.updateRoute(id, dto);
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return "Route deleted successfully";
    }
}