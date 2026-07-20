package com.app.bustracking.service;
import com.app.bustracking.Request.ConductorRequest;
import com.app.bustracking.Response.ConductorResponse;

import java.util.List;

public interface ConductorService {
    List<ConductorResponse> getAll();
    ConductorResponse getById(Long id);
    ConductorResponse create(ConductorRequest request);
    ConductorResponse update(Long id, ConductorRequest request);
    void delete(Long id);
}