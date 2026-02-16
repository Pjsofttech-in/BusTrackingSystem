package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.BusStop;
import com.bus.tracking.bus_tracking_system.service.BusStopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = "https://pjsofttech.com")
@RequestMapping("/busstop")
public class BusStopController {

    private final BusStopService service;

    public BusStopController(BusStopService service) {
        this.service = service;
    }

    // Add a new stop
    @PostMapping("/add")
    public BusStop addStop(@RequestBody BusStop stop) {
        return service.addStop(stop);
    }

    // Get all stops of a bus
    @GetMapping("/bus/{busId}")
    public List<BusStop> getStopsByBus(@PathVariable Long busId) {
        return service.getStopsByBus(busId);
    }

    // Mark stop reached
    @PostMapping("/reach/{stopId}")
    public BusStop markStopReached(@PathVariable Long stopId) {
        return service.markStopReached(stopId);
    }

    // Count stops reached
    @GetMapping("/count/{busId}")
    public long countStopsReached(@PathVariable Long busId) {
        return service.countStopsReached(busId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBusStop(@PathVariable Long id) {
        service.deleteStop(id);
        return "Bus stop deleted";
    }
}
