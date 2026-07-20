package com.app.bustracking.service;

import com.app.bustracking.Request.StudentRequest;
import com.app.bustracking.Response.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAll();
    StudentResponse getById(Long id);
    StudentResponse create(StudentRequest request);
    StudentResponse update(Long id, StudentRequest request);
    void delete(Long id);
}