package com.app.bustracking.service;

import com.app.bustracking.dto.AcademicYearRequestDTO;
import com.app.bustracking.dto.AcademicYearResponseDTO;
import com.app.bustracking.mapper.AcademicYearMapper;
import com.app.bustracking.model.AcademicYearModel;
import com.app.bustracking.repository.AcademicYearRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcademicYearService {

    private final AcademicYearRepository repository;

    public AcademicYearService(AcademicYearRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AcademicYearResponseDTO create(AcademicYearRequestDTO dto) {
        AcademicYearModel entity = AcademicYearMapper.toEntity(dto);
        AcademicYearModel saved = repository.save(entity);
        return AcademicYearMapper.toDTO(saved);
    }

    public List<AcademicYearResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(AcademicYearMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AcademicYearResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(AcademicYearMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public AcademicYearResponseDTO update(Long id, AcademicYearRequestDTO dto) {
        AcademicYearModel entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AcademicYear not found"));
        entity.setYearName(dto.getYearName());
        AcademicYearModel updated = repository.save(entity);
        return AcademicYearMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}