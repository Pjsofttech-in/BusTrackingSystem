package com.app.bustracking.service;

import com.app.bustracking.dto.MediumRequestDTO;
import com.app.bustracking.dto.MediumResponseDTO;
import com.app.bustracking.mapper.MediumMapper;
import com.app.bustracking.model.MediumModel;
import com.app.bustracking.repository.MediumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediumService {

    private final MediumRepository repository;

    public MediumService(MediumRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public MediumResponseDTO create(MediumRequestDTO dto) {
        MediumModel entity = MediumMapper.toEntity(dto);
        MediumModel saved = repository.save(entity);
        return MediumMapper.toDTO(saved);
    }

    public List<MediumResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(MediumMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MediumResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(MediumMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public MediumResponseDTO update(Long id, MediumRequestDTO dto) {
        MediumModel entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medium not found"));
        entity.setMediumName(dto.getMediumName());
        MediumModel updated = repository.save(entity);
        return MediumMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}