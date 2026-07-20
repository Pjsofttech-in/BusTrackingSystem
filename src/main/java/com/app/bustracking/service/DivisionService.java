package com.app.bustracking.service;

import com.app.bustracking.Request.DivisionRequest;
import com.app.bustracking.Response.DivisionResponse;

import java.util.List;

public interface DivisionService {

    List<DivisionResponse> getAll();

    DivisionResponse getById(Long id);

    DivisionResponse create(DivisionRequest request);

    DivisionResponse update(Long id, DivisionRequest request);

    void delete(Long id);
}