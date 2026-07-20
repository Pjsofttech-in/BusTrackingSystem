package com.app.bustracking.service;

import com.app.bustracking.Request.BusRequest;
import com.app.bustracking.Response.BusResponse;
import java.util.List;

public interface BusService {
    List<BusResponse> getAll();
    BusResponse getById(Long id);
    BusResponse create(BusRequest request);
    BusResponse update(Long id, BusRequest request);
    void delete(Long id);
}