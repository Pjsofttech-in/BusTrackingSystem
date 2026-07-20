// src/main/java/com/app/bustracking/service/BusStopService.java
package com.app.bustracking.service;

import com.app.bustracking.Request.BusStopRequest;
import com.app.bustracking.Response.BusStopResponse;

import java.util.List;

public interface BusStopService {
    List<BusStopResponse> getAll();
    BusStopResponse getById(Long id);
    BusStopResponse create(BusStopRequest request);
    BusStopResponse update(Long id, BusStopRequest request);
    void delete(Long id);
    BusStopResponse markReached(Long id);
}