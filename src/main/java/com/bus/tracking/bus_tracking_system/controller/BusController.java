package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.BusRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusResponseDTO;
import com.bus.tracking.bus_tracking_system.service.BusService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    // ADD BUS
    @PostMapping("/addBus")
    public ResponseEntity<BusResponseDTO> addBus(@RequestBody BusRequestDTO dto) {
        BusResponseDTO bus = busService.addBus(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bus);
    }

    // GET ALL BUSES
    @GetMapping("/getALLBuses")
    public ResponseEntity<List<BusResponseDTO>> getAllBuses() {
        List<BusResponseDTO> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    // GET BUS BY ID
    @GetMapping("/getBusById/{id}")
    public ResponseEntity<BusResponseDTO> getBusById(@PathVariable Long id) {
        BusResponseDTO bus = busService.getBusById(id);
        return ResponseEntity.ok(bus);
    }

//     GET BUS BY BUS NUMBER
    @GetMapping("/by-number/{busNumber}")
    public ResponseEntity<BusResponseDTO> getBusByNumber(@PathVariable String busNumber) {
        BusResponseDTO bus = busService.getBusByNumber(busNumber);
        return ResponseEntity.ok(bus);
    }

    // GET BUSES MATCHING PROVIDER BUS NUMBER
    // Returns buses where bus.busNumber = serviceProvider.busNumber
    @GetMapping("/matching-provider-bus-number")
    public ResponseEntity<List<BusResponseDTO>> getBusesMatchingProviderBusNumber() {
        List<BusResponseDTO> buses = busService.getBusesMatchingProviderBusNumber();
        return ResponseEntity.ok(buses);
    }

    // GET BUSES BY SERVICE PROVIDER BUS NUMBER (JOIN)
    @GetMapping("/by-provider-bus-number/{providerBusNumber}")
    public ResponseEntity<List<BusResponseDTO>> getBusesByProviderBusNumber(
            @PathVariable String providerBusNumber) {
        List<BusResponseDTO> buses = busService.getBusesByProviderBusNumber(providerBusNumber);
        return ResponseEntity.ok(buses);
    }

    // GET ACTIVE BUSES MATCHING PROVIDER BUS NUMBER
    @GetMapping("/active-matching-provider-bus-number")
    public ResponseEntity<List<BusResponseDTO>> getActiveBusesMatchingProviderBusNumber() {
        List<BusResponseDTO> buses = busService.getActiveBusesMatchingProviderBusNumber();
        return ResponseEntity.ok(buses);
    }

    // DAILY RUNNING
    @GetMapping("/daily-running")
    public ResponseEntity<List<BusResponseDTO>> getDailyRunning() {
        List<BusResponseDTO> buses = busService.getRunningBuses();
        return ResponseEntity.ok(buses);
    }

    // UPDATE BUS
    @PutMapping("/updateBus/{id}")
    public ResponseEntity<BusResponseDTO> updateBus(@PathVariable Long id,
                                                    @RequestBody BusRequestDTO dto) {
        BusResponseDTO updatedBus = busService.updateBus(id, dto);
        return ResponseEntity.ok(updatedBus);
    }

    // DELETE BUS
    @DeleteMapping("/deleteBus/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return ResponseEntity.ok("Bus deleted successfully");
    }
}