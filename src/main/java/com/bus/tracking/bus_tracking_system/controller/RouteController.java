package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.RouteRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.RouteResponseDTO;
import com.bus.tracking.bus_tracking_system.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://pjsofttech.com")
@RestController
//@RequestMapping("/route")
public class RouteController {

    private final RouteService service;

    public RouteController(RouteService service) {
        this.service = service;
    }

    @PostMapping("/addRoute")
    public RouteResponseDTO addRoute(@RequestBody RouteRequestDTO dto) {
        return service.addRoute(dto);
    }

    @GetMapping("/getAllRoutes")
    public List<RouteResponseDTO> getAllRoutes() {
        return service.getAllRoutes();
    }

    @GetMapping("/getRouteById/{id}")
    public RouteResponseDTO getRoute(@PathVariable Long id) {
        return service.getRouteById(id);
    }

    @PutMapping("/update/{id}")
    public RouteResponseDTO updateRoute(@PathVariable Long id,
                                        @RequestBody RouteRequestDTO dto) {
        return service.updateRoute(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Long id) {
        service.deleteRoute(id);
        return "Route deleted";
    }
}