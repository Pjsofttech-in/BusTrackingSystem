package com.app.bustracking.controller;

import com.app.bustracking.Request.BusLocationRequest;
import com.app.bustracking.Response.BusLocationResponse;
import com.app.bustracking.service.BusLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus-locations")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class BusLocationController {

    private final BusLocationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusLocationResponse create(@Valid @RequestBody BusLocationRequest request) {
        return service.saveLocation(request);
    }

    @GetMapping("/bus/{busId}/latest")
    public BusLocationResponse getLatest(@PathVariable Long busId) {
        return service.getLatestByBusId(busId);
    }

    @GetMapping("/bus/{busId}/history")
    public List<BusLocationResponse> getHistory(
            @PathVariable Long busId,
            @RequestParam(defaultValue = "20") int limit) {
        return service.getHistoryByBusId(busId, limit);
    }

    @GetMapping
    public List<BusLocationResponse> getAll() {
        return service.getAll();
    }
}