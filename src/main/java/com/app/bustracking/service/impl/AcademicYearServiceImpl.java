package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.AcademicYearMapper;
import com.app.bustracking.Model.AcademicYearModel;
import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.repository.AcademicYearRepository;
import com.app.bustracking.Request.AcademicYearRequest;
import com.app.bustracking.Response.AcademicYearResponse;
import com.app.bustracking.service.AcademicYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcademicYearServiceImpl implements AcademicYearService {

    private final AcademicYearRepository repository;
    private final AcademicYearMapper mapper;

    @Override
    public List<AcademicYearResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AcademicYearResponse getById(Long id) {
        AcademicYearModel model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic Year not found with id: " + id));
        return mapper.toResponse(model);
    }

    @Override
    @Transactional
    public AcademicYearResponse create(AcademicYearRequest request) {
        AcademicYearModel model = mapper.toModel(request);
        AcademicYearModel saved = repository.save(model);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public AcademicYearResponse update(Long id, AcademicYearRequest request) {
        AcademicYearModel existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic Year not found with id: " + id));
        existing.setYearName(request.yearName());
        AcademicYearModel updated = repository.save(existing);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Academic Year not found with id: " + id);
        }
        repository.deleteById(id);
    }
}