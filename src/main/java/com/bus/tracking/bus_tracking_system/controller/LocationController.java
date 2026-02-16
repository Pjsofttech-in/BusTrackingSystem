package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.BusLocation;
import com.bus.tracking.bus_tracking_system.service.BusLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final BusLocationService service;

    public LocationController(BusLocationService service) {
        this.service = service;
    }

    @PostMapping("/update")
    public BusLocation updateLocation(@RequestBody BusLocation location) {
        return service.updateLocation(location);
    }

    @GetMapping("/latest/{busId}")
    public BusLocation getLatestLocation(@PathVariable Long busId) {
        return service.getLatestLocation(busId);
    }

    @GetMapping("/history/{busId}")
    public List<BusLocation> getLocationHistory(@PathVariable Long busId) {
        return service.getLocationHistory(busId);
    }
}
