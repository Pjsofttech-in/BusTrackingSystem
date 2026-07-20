package com.app.bustracking.service;

import com.app.bustracking.Request.BusTripRequest;
import com.app.bustracking.Response.BusTripResponse;

import java.util.List;

public interface BusTripService {
    List<BusTripResponse> getAll();
    BusTripResponse getById(Long id);
    BusTripResponse create(BusTripRequest request);
    BusTripResponse update(Long id, BusTripRequest request);
    void delete(Long id);
}