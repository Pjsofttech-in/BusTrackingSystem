package com.app.bustracking.service;

import com.app.bustracking.dto.DirectionRequestDTO;
import com.app.bustracking.dto.DirectionResponseDTO;
import com.app.bustracking.mapper.DirectionMapper;
import com.app.bustracking.model.DirectionModel;
import com.app.bustracking.repository.DirectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectionService {

    private final DirectionRepository repository;

    public DirectionService(DirectionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DirectionResponseDTO create(DirectionRequestDTO dto) {
        DirectionModel entity = DirectionMapper.toEntity(dto);
        DirectionModel saved = repository.save(entity);
        return DirectionMapper.toDTO(saved);
    }

    public List<DirectionResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(DirectionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DirectionResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(DirectionMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public DirectionResponseDTO update(Long id, DirectionRequestDTO dto) {
        DirectionModel entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Direction not found"));
        entity.setName(dto.getName());
        entity.setAbbreviation(dto.getAbbreviation());
        entity.setMinDegrees(dto.getMinDegrees());
        entity.setMaxDegrees(dto.getMaxDegrees());
        entity.setDescription(dto.getDescription());
        DirectionModel updated = repository.save(entity);
        return DirectionMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}