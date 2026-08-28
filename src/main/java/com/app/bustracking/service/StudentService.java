package com.app.bustracking.service;

import com.app.bustracking.dto.StudentRequestDTO;
import com.app.bustracking.dto.StudentResponseDTO;
import com.app.bustracking.mapper.StudentMapper;
import com.app.bustracking.model.BusRouteModel;
import com.app.bustracking.model.StudentModel;
import com.app.bustracking.repository.BusRouteRepository;
import com.app.bustracking.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final BusRouteRepository routeRepository;

    public StudentService(StudentRepository repository, BusRouteRepository routeRepository) {
        this.repository = repository;
        this.routeRepository = routeRepository;
    }

    @Transactional
    public StudentResponseDTO create(StudentRequestDTO dto) {
        BusRouteModel route = null;
        if (dto.getRouteId() != null) {
            route = routeRepository.findById(dto.getRouteId())
                    .orElseThrow(() -> new RuntimeException("Route not found"));
        }
        StudentModel entity = StudentMapper.toEntity(dto, route);
        StudentModel saved = repository.save(entity);
        return StudentMapper.toDTO(saved);
    }

    public List<StudentResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(StudentMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public StudentResponseDTO update(Long id, StudentRequestDTO dto) {
        StudentModel student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(dto.getName());
        student.setRollNumber(dto.getRollNumber());
        student.setAdmission(dto.getAdmission());
        student.setPresent(dto.getPresent());
        student.setStatus(dto.getStatus());
        student.setInBus(dto.getInBus());
        student.setQrImageUrl(dto.getQrImageUrl());
        student.setParentName(dto.getParentName());
        student.setParentPhone(dto.getParentPhone());
        student.setParentEmail(dto.getParentEmail());
        student.setBloodGroup(dto.getBloodGroup());
        student.setAge(dto.getAge());
        student.setStudentClass(dto.getStudentClass());
        student.setDivision(dto.getDivision());
        student.setMedium(dto.getMedium());
        student.setAcademicYear(dto.getAcademicYear());

        if (dto.getRouteId() != null) {
            BusRouteModel route = routeRepository.findById(dto.getRouteId())
                    .orElseThrow(() -> new RuntimeException("Route not found"));
            student.setRoute(route);
        } else {
            student.setRoute(null);
        }

        StudentModel updated = repository.save(student);
        return StudentMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}