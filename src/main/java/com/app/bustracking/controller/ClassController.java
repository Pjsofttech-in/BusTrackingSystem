package com.app.bustracking.controller;

import com.app.bustracking.Request.ClassRequest;
import com.app.bustracking.Response.ClassResponse;
import com.app.bustracking.service.ClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService service;

    @GetMapping
    public List<ClassResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ClassResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassResponse create(@Valid @RequestBody ClassRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ClassResponse update(@PathVariable Long id, @Valid @RequestBody ClassRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}