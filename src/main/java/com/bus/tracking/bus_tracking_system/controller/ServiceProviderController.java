package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.ServiceProvider;
import com.bus.tracking.bus_tracking_system.repository.ServiceProviderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service-provider")
@CrossOrigin
public class ServiceProviderController {

    private final ServiceProviderRepository repository;

    public ServiceProviderController(ServiceProviderRepository repository) {
        this.repository = repository;
    }

    // Add a new provider
    @PostMapping("/add")
    public ServiceProvider add(@RequestBody ServiceProvider provider) {
        return repository.save(provider);
    }

    // Get all providers
    @GetMapping("/all")
    public List<ServiceProvider> all() {
        return repository.findAll();
    }

    // Update a provider
    @PutMapping("/update/{id}")
    public ServiceProvider updateProvider(@PathVariable Long id, @RequestBody ServiceProvider updated) {
        Optional<ServiceProvider> optional = repository.findById(id);
        if (optional.isEmpty()) return null;

        ServiceProvider existing = optional.get();
        existing.setName(updated.getName());
        existing.setMobile(updated.getMobile());
        existing.setEmail(updated.getEmail());

        return repository.save(existing); // Use save to update
    }

    // Delete a provider
    @DeleteMapping("/delete/{id}")
    public String deleteProvider(@PathVariable Long id) {
        repository.deleteById(id); // Use deleteById
        return "Provider deleted";
    }
}
