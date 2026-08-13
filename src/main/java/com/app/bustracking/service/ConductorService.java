package com.app.bustracking.service;

import com.app.bustracking.dto.ConductorRequestDTO;
import com.app.bustracking.dto.ConductorResponseDTO;
import com.app.bustracking.mapper.ConductorMapper;
import com.app.bustracking.model.ConductorModel;
import com.app.bustracking.repository.ConductorRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConductorService {

    private final ConductorRepository repository;
//    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public ConductorService(ConductorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ConductorResponseDTO create(ConductorRequestDTO dto) {
        ConductorModel conductor = ConductorMapper.toEntity(dto);
//        if (conductor.getPassword() != null && !conductor.getPassword().isEmpty()) {
//            conductor.setPassword(encoder.encode(conductor.getPassword()));
//        }
        ConductorModel saved = repository.save(conductor);
        return ConductorMapper.toDTO(saved);
    }

    public List<ConductorResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(ConductorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ConductorResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(ConductorMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public ConductorResponseDTO update(Long id, ConductorRequestDTO dto) {
        ConductorModel conductor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor not found"));
        conductor.setName(dto.getName());
        conductor.setPhone(dto.getPhone());
        conductor.setEmail(dto.getEmail());
        conductor.setLicenseNumber(dto.getLicenseNumber());
        conductor.setIdCard(dto.getIdCard());

        conductor.setLicensePhoto(dto.getLicensePhoto());
        conductor.setConductorPhoto(dto.getConductorPhoto());
        conductor.setLicenseExpiryDate(dto.getLicenseExpiryDate());
        conductor.setExperienceYears(dto.getExperienceYears());
        conductor.setStatus(dto.getStatus());
        conductor.setJoiningDate(dto.getJoiningDate());
        conductor.setTerminateDate(dto.getTerminateDate());
        conductor.setHouseNo(dto.getHouseNo());
        conductor.setStreet(dto.getStreet());
        conductor.setCity(dto.getCity());
        conductor.setState(dto.getState());
        conductor.setPincode(dto.getPincode());
//        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
//            conductor.setPassword(encoder.encode(dto.getPassword()));
//        }
        ConductorModel updated = repository.save(conductor);
        return ConductorMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ConductorModel findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor not found"));
    }
}