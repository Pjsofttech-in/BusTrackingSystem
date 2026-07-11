package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.RouteStopDTO;
import com.bus.tracking.bus_tracking_system.service.RouteStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stops")
//@CrossOrigin(origins = "https://pjsofttech.com")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class RouteStopController {

    private final RouteStopService service;

    //  ADD STOP TO ROUTE
    @PostMapping("/{routeId}")
    public RouteStopDTO addStop(@PathVariable Long routeId,
                                @RequestBody RouteStopDTO dto) {
        return service.addStop(routeId, dto);
    }

    // GET ALL
    @GetMapping("/all")
    public List<RouteStopDTO> getAll() {
        return service.getAllStops();
    }

    //  GET BY ID
    @GetMapping("/{id}")
    public RouteStopDTO getById(@PathVariable Long id) {
        return service.getStop(id);
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteStop(id);
        return "Stop deleted successfully";
    }
}