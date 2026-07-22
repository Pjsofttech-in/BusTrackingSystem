package com.app.bustracking.service;

import com.app.bustracking.dto.DivisionRequestDTO;
import com.app.bustracking.dto.DivisionResponseDTO;
import com.app.bustracking.mapper.DivisionMapper;
import com.app.bustracking.model.DivisionModel;
import com.app.bustracking.repository.DivisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DivisionService {

    private final DivisionRepository repository;

    public DivisionService(DivisionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DivisionResponseDTO create(DivisionRequestDTO dto) {
        DivisionModel entity = DivisionMapper.toEntity(dto);
        DivisionModel saved = repository.save(entity);
        return DivisionMapper.toDTO(saved);
    }

    public List<DivisionResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(DivisionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DivisionResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(DivisionMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public DivisionResponseDTO update(Long id, DivisionRequestDTO dto) {
        DivisionModel entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Division not found"));
        entity.setDivisionName(dto.getDivisionName());
        DivisionModel updated = repository.save(entity);
        return DivisionMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}