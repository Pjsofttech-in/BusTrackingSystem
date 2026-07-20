package com.app.bustracking.controller;

import com.app.bustracking.Request.DivisionRequest;
import com.app.bustracking.Response.DivisionResponse;
import com.app.bustracking.service.DivisionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/divisions")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class DivisionController {

    private final DivisionService service;

    @GetMapping
    public List<DivisionResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DivisionResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DivisionResponse create(@Valid @RequestBody DivisionRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public DivisionResponse update(@PathVariable Long id, @Valid @RequestBody DivisionRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}