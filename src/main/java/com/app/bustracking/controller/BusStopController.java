// src/main/java/com/app/bustracking/controller/BusStopController.java
package com.app.bustracking.controller;

import com.app.bustracking.Request.BusStopRequest;
import com.app.bustracking.Response.BusStopResponse;
import com.app.bustracking.service.BusStopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus-stops")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class BusStopController {

    private final BusStopService busStopService;

    @GetMapping
    public List<BusStopResponse> getAll() {
        return busStopService.getAll();
    }

    @GetMapping("/{id}")
    public BusStopResponse getById(@PathVariable Long id) {
        return busStopService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusStopResponse create(@Valid @RequestBody BusStopRequest request) {
        return busStopService.create(request);
    }

    @PutMapping("/{id}")
    public BusStopResponse update(@PathVariable Long id, @Valid @RequestBody BusStopRequest request) {
        return busStopService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        busStopService.delete(id);
    }

    @PatchMapping("/{id}/reached")
    public BusStopResponse markReached(@PathVariable Long id) {
        return busStopService.markReached(id);
    }
}