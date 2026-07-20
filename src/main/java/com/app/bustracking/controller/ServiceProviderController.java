package com.app.bustracking.controller;

import com.app.bustracking.Request.ServiceProviderRequest;
import com.app.bustracking.Response.ServiceProviderResponse;
import com.app.bustracking.service.ServiceProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/service-providers")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class ServiceProviderController {

    private final ServiceProviderService service;

    @GetMapping
    public ResponseEntity<List<ServiceProviderResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceProviderResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ServiceProviderResponse> create(@RequestBody ServiceProviderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceProviderResponse> update(@PathVariable Long id,
                                                          @RequestBody ServiceProviderRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}