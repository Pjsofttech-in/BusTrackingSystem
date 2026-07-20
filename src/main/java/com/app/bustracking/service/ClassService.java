package com.app.bustracking.service;

import com.app.bustracking.Request.ClassRequest;
import com.app.bustracking.Response.ClassResponse;

import java.util.List;

public interface ClassService {

    List<ClassResponse> getAll();

    ClassResponse getById(Long id);

    ClassResponse create(ClassRequest request);

    ClassResponse update(Long id, ClassRequest request);

    void delete(Long id);
}