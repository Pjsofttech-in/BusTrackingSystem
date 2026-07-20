package com.app.bustracking.controller;

import com.app.bustracking.Request.MediumRequest;
import com.app.bustracking.Response.MediumResponse;
import com.app.bustracking.service.MediumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mediums")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class MediumController {

    private final MediumService service;

    @GetMapping
    public List<MediumResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MediumResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MediumResponse create(@Valid @RequestBody MediumRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public MediumResponse update(@PathVariable Long id, @Valid @RequestBody MediumRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}