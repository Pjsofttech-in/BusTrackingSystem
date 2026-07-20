package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.ClassMapper;
import com.app.bustracking.Model.ClassModel;
import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.repository.ClassRepository;
import com.app.bustracking.Request.ClassRequest;
import com.app.bustracking.Response.ClassResponse;
import com.app.bustracking.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository repository;
    private final ClassMapper mapper;

    @Override
    public List<ClassResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClassResponse getById(Long id) {
        ClassModel model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + id));
        return mapper.toResponse(model);
    }

    @Override
    @Transactional
    public ClassResponse create(ClassRequest request) {
        ClassModel model = mapper.toModel(request);
        ClassModel saved = repository.save(model);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ClassResponse update(Long id, ClassRequest request) {
        ClassModel existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + id));
        existing.setName(request.name());
        ClassModel updated = repository.save(existing);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Class not found with id: " + id);
        }
        repository.deleteById(id);
    }
}