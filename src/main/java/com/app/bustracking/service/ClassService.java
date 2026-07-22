package com.app.bustracking.service;

import com.app.bustracking.dto.ClassRequestDTO;
import com.app.bustracking.dto.ClassResponseDTO;
import com.app.bustracking.mapper.ClassMapper;
import com.app.bustracking.model.ClassModel;
import com.app.bustracking.repository.ClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {

    private final ClassRepository repository;

    public ClassService(ClassRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ClassResponseDTO create(ClassRequestDTO dto) {
        ClassModel entity = ClassMapper.toEntity(dto);
        ClassModel saved = repository.save(entity);
        return ClassMapper.toDTO(saved);
    }

    public List<ClassResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(ClassMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClassResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(ClassMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public ClassResponseDTO update(Long id, ClassRequestDTO dto) {
        ClassModel entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        entity.setName(dto.getName());
        ClassModel updated = repository.save(entity);
        return ClassMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}