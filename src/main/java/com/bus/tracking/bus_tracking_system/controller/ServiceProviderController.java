package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.ServiceProviderRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.ServiceProviderResponseDTO;
import com.bus.tracking.bus_tracking_system.service.ServiceProviderService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-provider")
//@CrossOrigin(origins = "https://pjsofttech.com")
@CrossOrigin(origins = "http://localhost:5173")
public class ServiceProviderController {

    private final ServiceProviderService service;

    public ServiceProviderController(ServiceProviderService service) {
        this.service = service;
    }

    // ADD
    @PostMapping("/add")
    public ServiceProviderResponseDTO add(
            @RequestBody ServiceProviderRequestDTO dto) {

        return service.addServiceProvider(dto);
    }

    // GET ALL
    @GetMapping("/all")
    public List<ServiceProviderResponseDTO> all() {
        return service.getAllServiceProviders();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ServiceProviderResponseDTO getById(
            @PathVariable Long id) {

        return service.getServiceProviderById(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ServiceProviderResponseDTO updateProvider(
            @PathVariable Long id,
            @RequestBody ServiceProviderRequestDTO dto) {

        return service.updateProvider(id, dto);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteProvider(@PathVariable Long id) {
        service.deleteProvider(id);
        return "Provider deleted";
    }
}