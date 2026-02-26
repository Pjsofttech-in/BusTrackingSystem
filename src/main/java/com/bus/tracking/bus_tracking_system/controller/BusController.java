package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.BusRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusResponseDTO;
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

    // ADD
    @PostMapping("/add")
    public BusResponseDTO addBus(@RequestBody BusRequestDTO dto) {
        return service.addBus(dto);
    }

    // GET ALL
    @GetMapping("/all")
    public List<BusResponseDTO> getAllBuses() {
        return service.getAllBuses();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public BusResponseDTO getBus(@PathVariable Long id) {
        return service.getBusById(id);
    }

    // DAILY RUNNING
    @GetMapping("/daily-running")
    public List<BusResponseDTO> getDailyRunning() {
        return service.getRunningBuses();
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteBus(@PathVariable Long id) {
        service.deleteBus(id);
        return "Bus deleted";
    }
}