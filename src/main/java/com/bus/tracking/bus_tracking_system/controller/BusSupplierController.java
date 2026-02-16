package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.BusSupplier;
import com.bus.tracking.bus_tracking_system.service.BusSupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@CrossOrigin
public class BusSupplierController {

    private final BusSupplierService service;

    public BusSupplierController(BusSupplierService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public BusSupplier add(@RequestBody BusSupplier supplier) {
        return service.addSupplier(supplier);
    }

    @GetMapping("/all")
    public List<BusSupplier> all() {
        return service.getAll();
    }

    @PutMapping("/update/{id}")
    public BusSupplier updateSupplier(@PathVariable Long id, @RequestBody BusSupplier updated) {
        BusSupplier existing = service.getById(id); // add getById in service
        if (existing == null) return null;

        existing.setSupplierName(updated.getSupplierName());
        existing.setCompanyName(updated.getCompanyName());
        existing.setMobile(updated.getMobile());
        existing.setEmail(updated.getEmail());
        existing.setAddress(updated.getAddress());

        return service.addSupplier(existing);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        service.deleteSupplier(id);
        return "Supplier deleted";
    }

}
