package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.MediumMapper;
import com.app.bustracking.Model.MediumModel;
import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.repository.MediumRepository;
import com.app.bustracking.Request.MediumRequest;
import com.app.bustracking.Response.MediumResponse;
import com.app.bustracking.service.MediumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediumServiceImpl implements MediumService {

    private final MediumRepository repository;
    private final MediumMapper mapper;

    @Override
    public List<MediumResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MediumResponse getById(Long id) {
        MediumModel model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medium not found with id: " + id));
        return mapper.toResponse(model);
    }

    @Override
    @Transactional
    public MediumResponse create(MediumRequest request) {
        MediumModel model = mapper.toModel(request);
        MediumModel saved = repository.save(model);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public MediumResponse update(Long id, MediumRequest request) {
        MediumModel existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medium not found with id: " + id));
        existing.setMediumName(request.mediumName());
        MediumModel updated = repository.save(existing);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Medium not found with id: " + id);
        }
        repository.deleteById(id);
    }
}