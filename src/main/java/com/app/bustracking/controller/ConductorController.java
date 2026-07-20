// src/main/java/com/app/bustracking/controller/ConductorController.java
package com.app.bustracking.controller;

import com.app.bustracking.Request.ConductorRequest;
import com.app.bustracking.Response.ConductorResponse;
import com.app.bustracking.service.ConductorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conductors")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class ConductorController {

    private final ConductorService conductorService;

    @GetMapping
    public List<ConductorResponse> getAll() {
        return conductorService.getAll();
    }

    @GetMapping("/{id}")
    public ConductorResponse getById(@PathVariable Long id) {
        return conductorService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConductorResponse create(@Valid @RequestBody ConductorRequest request) {
        return conductorService.create(request);
    }

    @PutMapping("/{id}")
    public ConductorResponse update(@PathVariable Long id, @Valid @RequestBody ConductorRequest request) {
        return conductorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        conductorService.delete(id);
    }
}