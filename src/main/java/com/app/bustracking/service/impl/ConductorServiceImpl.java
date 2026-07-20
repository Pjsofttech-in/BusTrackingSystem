// src/main/java/com/app/bustracking/service/impl/ConductorServiceImpl.java
package com.app.bustracking.service.impl;

import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.Mapper.ConductorMapper;
import com.app.bustracking.Model.ConductorModel;
import com.app.bustracking.repository.ConductorRepository;
import com.app.bustracking.Request.ConductorRequest;
import com.app.bustracking.Response.ConductorResponse;
import com.app.bustracking.service.ConductorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConductorServiceImpl implements ConductorService {

    private final ConductorRepository conductorRepository;
    private final ConductorMapper conductorMapper;

    @Override
    public List<ConductorResponse> getAll() {
        return conductorRepository.findAll().stream()
                .map(conductorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ConductorResponse getById(Long id) {
        ConductorModel conductor = conductorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conductor not found with id: " + id));
        return conductorMapper.toResponse(conductor);
    }

    @Override
    @Transactional
    public ConductorResponse create(ConductorRequest request) {
        ConductorModel conductor = conductorMapper.toModel(request);
        // createdAt is set by @PrePersist
        ConductorModel saved = conductorRepository.save(conductor);
        return conductorMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ConductorResponse update(Long id, ConductorRequest request) {
        ConductorModel existing = conductorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conductor not found with id: " + id));

        // Update fields
        existing.setName(request.name());
        existing.setPhone(request.phone());
        existing.setEmail(request.email());
        existing.setPassword(request.password());
        existing.setLicenseNumber(request.licenseNumber());
        existing.setIdCard(request.idCard());
        existing.setLicenseType(request.licenseType());
        existing.setLicensePhoto(request.licensePhoto());
        existing.setConductorPhoto(request.conductorPhoto());
        existing.setLicenseExpiryDate(request.licenseExpiryDate());
        existing.setExperienceYears(request.experienceYears());
        existing.setStatus(request.status());
        existing.setJoiningDate(request.joiningDate());
        existing.setTerminateDate(request.terminateDate());
        existing.setHouseNo(request.houseNo());
        existing.setStreet(request.street());
        existing.setCity(request.city());
        existing.setState(request.state());
        existing.setPincode(request.pincode());

        ConductorModel updated = conductorRepository.save(existing);
        return conductorMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!conductorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Conductor not found with id: " + id);
        }
        conductorRepository.deleteById(id);
    }
}