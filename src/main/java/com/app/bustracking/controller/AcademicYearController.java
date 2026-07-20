package com.app.bustracking.controller;

import com.app.bustracking.Request.AcademicYearRequest;
import com.app.bustracking.Response.AcademicYearResponse;
import com.app.bustracking.service.AcademicYearService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic-years")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class AcademicYearController {

    private final AcademicYearService service;

    @GetMapping
    public List<AcademicYearResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AcademicYearResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AcademicYearResponse create(@Valid @RequestBody AcademicYearRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public AcademicYearResponse update(@PathVariable Long id, @Valid @RequestBody AcademicYearRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}