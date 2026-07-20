package com.app.bustracking.controller;

import com.app.bustracking.Request.BusRouteRequest;
import com.app.bustracking.Response.BusRouteResponse;
import com.app.bustracking.service.BusRouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus-routes")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class BusRouteController {

    private final BusRouteService busRouteService;

    @GetMapping
    public List<BusRouteResponse> getAll() {
        return busRouteService.getAll();
    }

    @GetMapping("/{id}")
    public BusRouteResponse getById(@PathVariable Long id) {
        return busRouteService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusRouteResponse create(@Valid @RequestBody BusRouteRequest request) {
        return busRouteService.create(request);
    }

    @PutMapping("/{id}")
    public BusRouteResponse update(@PathVariable Long id, @Valid @RequestBody BusRouteRequest request) {
        return busRouteService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        busRouteService.delete(id);
    }
}