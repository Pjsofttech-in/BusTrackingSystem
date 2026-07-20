package com.app.bustracking.service.impl;

import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.Mapper.DriverMapper;
import com.app.bustracking.Model.DriverModel;
import com.app.bustracking.repository.DriverRepository;
import com.app.bustracking.Request.DriverRequest;
import com.app.bustracking.Response.DriverResponse;
import com.app.bustracking.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public List<DriverResponse> getAll() {
        return driverRepository.findAll().stream()
                .map(driverMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DriverResponse getById(Long id) {
        DriverModel driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + id));
        return driverMapper.toResponse(driver);
    }

    @Override
    @Transactional
    public DriverResponse create(DriverRequest request) {
        DriverModel driver = driverMapper.toModel(request);
        // createdAt is set by @PrePersist
        DriverModel saved = driverRepository.save(driver);
        return driverMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public DriverResponse update(Long id, DriverRequest request) {
        DriverModel existing = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + id));

        // Update scalar fields
        existing.setName(request.name());
        existing.setPhone(request.phone());
        existing.setEmail(request.email());
        existing.setPassword(request.password());
        existing.setLicenseNumber(request.licenseNumber());
        existing.setIdCard(request.idCard());
        existing.setLicenseType(request.licenseType());
        existing.setLicensePhoto(request.licensePhoto());
        existing.setDriverPhoto(request.driverPhoto());
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

        DriverModel updated = driverRepository.save(existing);
        return driverMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!driverRepository.existsById(id)) {
            throw new ResourceNotFoundException("Driver not found with id: " + id);
        }
        driverRepository.deleteById(id);
    }
}