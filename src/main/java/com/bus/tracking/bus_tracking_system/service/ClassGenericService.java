package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.ClassGeneric;
import com.bus.tracking.bus_tracking_system.repository.ClassGenericRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassGenericService {

    private final ClassGenericRepository repository;

    public ClassGenericService(ClassGenericRepository repository) {
        this.repository = repository;
    }

    public ClassGeneric save(ClassGeneric classGeneric) {
        return repository.save(classGeneric);
    }

    public List<ClassGeneric> getAll() {
        return repository.findAll();
    }


    public ClassGeneric getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with id: " + id));
    }

    public ClassGeneric updateClass(Long id, ClassGeneric updatedClass) {

        ClassGeneric existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        existing.setName(updatedClass.getName());

        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Class not found with id: " + id);
        }
        repository.deleteById(id);
    }
}