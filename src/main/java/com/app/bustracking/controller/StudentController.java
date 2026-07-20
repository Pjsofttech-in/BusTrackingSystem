package com.app.bustracking.controller;

import com.app.bustracking.Request.StudentRequest;
import com.app.bustracking.Response.StudentResponse;
import com.app.bustracking.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping
    public List<StudentResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse create(@Valid @RequestBody StudentRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}