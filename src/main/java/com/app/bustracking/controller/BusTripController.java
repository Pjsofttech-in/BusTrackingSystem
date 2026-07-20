package com.app.bustracking.controller;

import com.app.bustracking.Request.BusTripRequest;
import com.app.bustracking.Response.BusTripResponse;
import com.app.bustracking.service.BusTripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus-trips")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class BusTripController {

    private final BusTripService service;

    @GetMapping
    public List<BusTripResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BusTripResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusTripResponse create(@Valid @RequestBody BusTripRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public BusTripResponse update(@PathVariable Long id, @Valid @RequestBody BusTripRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}