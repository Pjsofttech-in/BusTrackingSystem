package com.app.bustracking.service;

import com.app.bustracking.Request.BusLocationRequest;
import com.app.bustracking.Response.BusLocationResponse;
import java.util.List;

public interface BusLocationService {
    BusLocationResponse saveLocation(BusLocationRequest request);
    BusLocationResponse getLatestByBusId(Long busId);
    List<BusLocationResponse> getHistoryByBusId(Long busId, int limit);
    List<BusLocationResponse> getAll();
}