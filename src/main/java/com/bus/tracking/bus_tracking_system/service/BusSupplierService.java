package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.BusSupplierRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusSupplierResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.BusSupplierMapper;
import com.bus.tracking.bus_tracking_system.model.BusSupplier;
import com.bus.tracking.bus_tracking_system.repository.BusSupplierRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusSupplierService {

    private final BusSupplierRepository repository;

    public BusSupplierService(BusSupplierRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public BusSupplierResponseDTO addSupplier(BusSupplierRequestDTO dto) {

        BusSupplier supplier = BusSupplierMapper.toEntity(dto);

        BusSupplier saved = repository.save(supplier);

        return BusSupplierMapper.toDTO(saved);
    }

    // GET ALL
    public List<BusSupplierResponseDTO> getAll() {

        return repository.findAll()
                .stream()
                .map(BusSupplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    // DELETE
    public void deleteSupplier(Long id) {
        repository.deleteById(id);
    }

    // GET ENTITY (internal use only if needed)
    public BusSupplier getEntityById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // GET BY ID (DTO)
    public BusSupplierResponseDTO getById(Long id) {

        BusSupplier supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        return BusSupplierMapper.toDTO(supplier);
    }

    // UPDATE
    public BusSupplierResponseDTO updateSupplier(
            Long id,
            BusSupplierRequestDTO dto) {

        BusSupplier existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        existing.setSupplierName(dto.getSupplierName());
        existing.setCompanyName(dto.getCompanyName());
        existing.setMobile(dto.getMobile());
        existing.setEmail(dto.getEmail());
        existing.setAddress(dto.getAddress());

        BusSupplier updated = repository.save(existing);

        return BusSupplierMapper.toDTO(updated);
    }
}