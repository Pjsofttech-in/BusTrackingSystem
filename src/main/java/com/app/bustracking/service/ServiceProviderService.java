package com.app.bustracking.service;

import com.app.bustracking.Request.ServiceProviderRequest;
import com.app.bustracking.Response.ServiceProviderResponse;
import java.util.List;

public interface ServiceProviderService {
    List<ServiceProviderResponse> getAll();
    ServiceProviderResponse getById(Long id);
    ServiceProviderResponse create(ServiceProviderRequest request);
    ServiceProviderResponse update(Long id, ServiceProviderRequest request);
    void delete(Long id);
}