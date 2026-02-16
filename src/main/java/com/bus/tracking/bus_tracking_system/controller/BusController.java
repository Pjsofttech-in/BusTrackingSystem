package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.Bus;
import com.bus.tracking.bus_tracking_system.repository.BusRepository;
import com.bus.tracking.bus_tracking_system.service.BusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = "https://pjsofttech.com")
@RequestMapping("/bus")
public class BusController {

    private final BusService service;

    public BusController(BusService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Bus addBus(@RequestBody Bus bus) {
        return service.addBus(bus);
    }

    @GetMapping("/all")
    public List<Bus> getAllBuses() {
        return service.getAllBuses();
    }

    @GetMapping("/{id}")
    public Bus getBus(@PathVariable Long id) {
        return service.getBusById(id);
    }
    @GetMapping("/daily-running")
    public List<Bus> getDailyRunning() {
        return service.getRunningBuses();
    }

    @PutMapping("/update/{id}")
    public Bus updateBus(@PathVariable Long id, @RequestBody Bus updated) {
        Bus existing = service.getBusById(id);
        if (existing == null) return null;

        existing.setBusNumber(updated.getBusNumber());
        existing.setCapacity(updated.getCapacity());
        existing.setStatus(updated.getStatus());
        existing.setAssignedDriverId(updated.getAssignedDriverId());
        existing.setAssignedConductorId(updated.getAssignedConductorId());

        return service.addBus(existing); // save update
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBus(@PathVariable Long id) {
        service.deleteBus(id);
        return "Bus deleted";
    }
}
