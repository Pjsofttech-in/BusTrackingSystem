package com.app.bustracking.service;

import com.app.bustracking.Request.BusRouteRequest;
import com.app.bustracking.Response.BusRouteResponse;

import java.util.List;

public interface BusRouteService {
    List<BusRouteResponse> getAll();
    BusRouteResponse getById(Long id);
    BusRouteResponse create(BusRouteRequest request);
    BusRouteResponse update(Long id, BusRouteRequest request);
    void delete(Long id);
}