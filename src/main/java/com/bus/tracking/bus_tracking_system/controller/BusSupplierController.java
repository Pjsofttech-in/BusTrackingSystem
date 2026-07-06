package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.BusSupplierRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusSupplierResponseDTO;
import com.bus.tracking.bus_tracking_system.service.BusSupplierService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://pjsofttech.com")
@RequestMapping("/supplier")
public class BusSupplierController {



    private final BusSupplierService service;

    public BusSupplierController(BusSupplierService service) {
        this.service = service;
    }

    // ADD
    @PostMapping("/add")
    public BusSupplierResponseDTO add(
            @RequestBody BusSupplierRequestDTO dto) {

        return service.addSupplier(dto);
    }

    // GET ALL
    @GetMapping("/all")
    public List<BusSupplierResponseDTO> all() {
        return service.getAll();
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public BusSupplierResponseDTO updateSupplier(
            @PathVariable Long id,
            @RequestBody BusSupplierRequestDTO dto) {

        return service.updateSupplier(id, dto);
    }


    // DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        service.deleteSupplier(id);
        return "Supplier deleted";
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<BusSupplierResponseDTO> getSupplierById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getById(id));
    }
}