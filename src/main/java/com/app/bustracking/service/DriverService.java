package com.app.bustracking.service;

import com.app.bustracking.Request.DriverRequest;
import com.app.bustracking.Response.DriverResponse;

import java.util.List;

public interface DriverService {
    List<DriverResponse> getAll();
    DriverResponse getById(Long id);
    DriverResponse create(DriverRequest request);
    DriverResponse update(Long id, DriverRequest request);
    void delete(Long id);
}