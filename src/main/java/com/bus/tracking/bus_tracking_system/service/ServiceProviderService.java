package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.ServiceProvider;
import com.bus.tracking.bus_tracking_system.repository.ServiceProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviderService {

    private final ServiceProviderRepository repo;

    public ServiceProviderService(ServiceProviderRepository repo) {
        this.repo = repo;
    }

    // Add a new service provider
    public ServiceProvider addServiceProvider(ServiceProvider sp) {
        return repo.save(sp);
    }

    // Get all service providers
    public List<ServiceProvider> getAllServiceProviders() {
        return repo.findAll();
    }

    // Get by ID
    public ServiceProvider getServiceProviderById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // ServiceProviderService.java
    public void deleteProvider(Long id) {
        repo.deleteById(id);
    }
}
