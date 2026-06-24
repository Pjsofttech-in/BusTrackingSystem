package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.BusRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusResponseDTO;
import com.bus.tracking.bus_tracking_system.service.BusService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://pjsofttech.com")
//@RequestMapping("/bus")
public class BusController {

    private final BusService service;

    public BusController(BusService service) {
        this.service = service;
    }

    // ADD
    @PostMapping("/addBus")
    public BusResponseDTO addBus(@RequestBody BusRequestDTO dto) {
        return service.addBus(dto);
    }

    // GET ALL
    @GetMapping("/getALLBuses")
    public List<BusResponseDTO> getAllBuses() {
        return service.getAllBuses();
    }

    // GET BY ID
    @GetMapping("getBusById/{id}")
    public BusResponseDTO getBus(@PathVariable Long id) {
        return service.getBusById(id);
    }

    // DAILY RUNNING
    @GetMapping("/daily-running")
    public List<BusResponseDTO> getDailyRunning() {
        return service.getRunningBuses();
    }

    //UPDATE
    @PutMapping("/updateBus/{id}")
    public BusResponseDTO updateBus(
            @PathVariable Long id,
            @RequestBody BusRequestDTO dto) {

        return service.updateBus(id, dto);
    }

    // DELETE
    @DeleteMapping("/deleteBus/{id}")
    public String deleteBus(@PathVariable Long id) {
        service.deleteBus(id);
        return "Bus deleted";
    }
}