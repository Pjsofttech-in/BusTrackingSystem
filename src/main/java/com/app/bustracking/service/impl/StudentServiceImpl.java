package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.StudentMapper;
import com.app.bustracking.Model.StudentModel;
import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.repository.StudentRepository;
import com.app.bustracking.Request.StudentRequest;
import com.app.bustracking.Response.StudentResponse;
import com.app.bustracking.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Override
    public List<StudentResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse getById(Long id) {
        StudentModel student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return mapper.toResponse(student);
    }

    @Override
    @Transactional
    public StudentResponse create(StudentRequest request) {
        StudentModel student = mapper.toModel(request);
        student.setPresent(request.present() != null ? request.present() : false);
        student.setInBus(request.inBus() != null ? request.inBus() : false);
        student.setStatus(request.status() != null ? request.status() : "ACTIVE");
        StudentModel saved = repository.save(student);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public StudentResponse update(Long id, StudentRequest request) {
        StudentModel existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        mapper.updateModel(existing, request);
        // Ensure booleans aren't null
        if (existing.getPresent() == null) existing.setPresent(false);
        if (existing.getInBus() == null) existing.setInBus(false);
        StudentModel updated = repository.save(existing);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        repository.deleteById(id);
    }
}