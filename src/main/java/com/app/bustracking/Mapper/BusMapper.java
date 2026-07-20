package com.app.bustracking.Mapper;

import com.app.bustracking.Model.BusModel;
import com.app.bustracking.Model.ServiceProviderModel;
import com.app.bustracking.Request.BusRequest;
import com.app.bustracking.Response.BusResponse;
import com.app.bustracking.repository.ServiceProviderRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BusMapper {

    @Autowired
    protected ServiceProviderRepository providerRepository;

    @Mapping(target = "serviceProvider", expression = "java(fetchProvider(request.serviceProviderId()))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract BusModel toModel(BusRequest request);

    @Mapping(target = "serviceProviderId", source = "bus.serviceProvider.id")
    @Mapping(target = "serviceProviderName", source = "bus.serviceProvider.serviceprovidername")
    public abstract BusResponse toResponse(BusModel bus);

    protected ServiceProviderModel fetchProvider(Long id) {
        if (id == null) return null;
        return providerRepository.findById(id).orElse(null);
    }
}