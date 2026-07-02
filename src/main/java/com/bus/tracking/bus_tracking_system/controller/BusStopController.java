package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.BusStopRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusStopResponseDTO;
import com.bus.tracking.bus_tracking_system.service.BusStopService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://pjsofttech.com")
//@RequestMapping("/busstop")
public class BusStopController {

    private final BusStopService service;

    public BusStopController(BusStopService service) {
        this.service = service;
    }

    // ADD STOP
    @PostMapping("/addBusStop")
    public BusStopResponseDTO addStop(
            @RequestBody BusStopRequestDTO dto) {

        return service.addStop(dto);
    }
    // GET ALL STOPS
    @GetMapping("/getAllStops")
    public List<BusStopResponseDTO> getAllStops() {
        return service.getAllStops();
    }

    // GET STOPS BY BUS
    @GetMapping("/getBusStopByBus/{busId}")
    public List<BusStopResponseDTO> getStopsByBus(
            @PathVariable Long busId) {

        return service.getStopsByBus(busId);
    }

    // GET STOP BY ID
    @GetMapping("/getBusStop/{id}")
    public BusStopResponseDTO getStopById(
            @PathVariable Long id) {

        return service.getStopById(id);
    }

    // UPDATE STOP
    @PutMapping("/updateBusStop/{id}")
    public BusStopResponseDTO updateStop(
            @PathVariable Long id,
            @RequestBody BusStopRequestDTO dto) {

        return service.updateStop(id, dto);
    }

    // MARK REACHED
    @PostMapping("/reach/{stopId}")
    public BusStopResponseDTO markStopReached(
            @PathVariable Long stopId) {

        return service.markStopReached(stopId);
    }

    // COUNT
    @GetMapping("/count/{busId}")
    public long countStopsReached(
            @PathVariable Long busId) {

        return service.countStopsReached(busId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBusStop(@PathVariable Long id) {
        service.deleteStop(id);
        return "Bus stop deleted";
    }
}