package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.DivisionMapper;
import com.app.bustracking.Model.DivisionModel;
import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.repository.DivisionRepository;
import com.app.bustracking.Request.DivisionRequest;
import com.app.bustracking.Response.DivisionResponse;
import com.app.bustracking.service.DivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DivisionServiceImpl implements DivisionService {

    private final DivisionRepository repository;
    private final DivisionMapper mapper;

    @Override
    public List<DivisionResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DivisionResponse getById(Long id) {
        DivisionModel model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Division not found with id: " + id));
        return mapper.toResponse(model);
    }

    @Override
    @Transactional
    public DivisionResponse create(DivisionRequest request) {
        DivisionModel model = mapper.toModel(request);
        DivisionModel saved = repository.save(model);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public DivisionResponse update(Long id, DivisionRequest request) {
        DivisionModel existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Division not found with id: " + id));
        existing.setDivisionName(request.divisionName());
        DivisionModel updated = repository.save(existing);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Division not found with id: " + id);
        }
        repository.deleteById(id);
    }
}