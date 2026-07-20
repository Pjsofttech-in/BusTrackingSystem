package com.app.bustracking.controller;

import com.app.bustracking.Request.DriverRequest;
import com.app.bustracking.Response.DriverResponse;
import com.app.bustracking.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping
    public List<DriverResponse> getAll() {
        return driverService.getAll();
    }

    @GetMapping("/{id}")
    public DriverResponse getById(@PathVariable Long id) {
        return driverService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverResponse create(@Valid @RequestBody DriverRequest request) {
        return driverService.create(request);
    }

    @PutMapping("/{id}")
    public DriverResponse update(@PathVariable Long id, @Valid @RequestBody DriverRequest request) {
        return driverService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        driverService.delete(id);
    }
}