package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.ConductorRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.ConductorResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.ConductorMapper;
import com.bus.tracking.bus_tracking_system.model.Conductor;
import com.bus.tracking.bus_tracking_system.repository.ConductorRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConductorService {

    private final ConductorRepository repo;

    public ConductorService(ConductorRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public ConductorResponseDTO addConductor(ConductorRequestDTO dto) {

        Conductor conductor = ConductorMapper.toEntity(dto);

        Conductor saved = repo.save(conductor);

        return ConductorMapper.toDTO(saved);
    }

    // GET ALL
    public List<ConductorResponseDTO> getAllConductors() {

        return repo.findAll()
                .stream()
                .map(ConductorMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID (DTO)
    public ConductorResponseDTO getConductorById(Long id) {

        Conductor conductor = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor not found"));

        return ConductorMapper.toDTO(conductor);
    }

    // INTERNAL ENTITY (if ever needed)
    public Conductor getEntityById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // UPDATE
    public ConductorResponseDTO updateConductor(
            Long id,
            ConductorRequestDTO dto) {

        Conductor existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor not found"));

        existing.setName(dto.getName());
        existing.setPhone(dto.getPhone());
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setStatus(dto.getStatus());
        existing.setJoiningDate(dto.getJoiningDate());
        existing.setTerminateDate(dto.getTerminateDate());
        existing.setLicenseExpiryDate(dto.getLicenseExpiryDate());
        existing.setHouseNo(dto.getHouseNo());
        existing.setStreet(dto.getStreet());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setPincode(dto.getPincode());

        Conductor updated = repo.save(existing);

        return ConductorMapper.toDTO(updated);
    }

    public void deleteConductor(Long id) {
        repo.deleteById(id);
    }
}