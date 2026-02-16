package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.BusSupplier;
import com.bus.tracking.bus_tracking_system.repository.BusSupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusSupplierService {

    private final BusSupplierRepository repository;

    public BusSupplierService(BusSupplierRepository repository) {
        this.repository = repository;
    }

    public BusSupplier addSupplier(BusSupplier supplier) {
        return repository.save(supplier);
    }

    public List<BusSupplier> getAll() {
        return repository.findAll();
    }
    // BusSupplierService.java
    public void deleteSupplier(Long id) {
        repository.deleteById(id);
    }
    public BusSupplier getById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
