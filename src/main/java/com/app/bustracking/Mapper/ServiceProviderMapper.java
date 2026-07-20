package com.app.bustracking.Mapper;

import com.app.bustracking.Model.ServiceProviderModel;
import com.app.bustracking.Request.ServiceProviderRequest;
import com.app.bustracking.Response.ServiceProviderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceProviderMapper {
    ServiceProviderModel toModel(ServiceProviderRequest request);
    ServiceProviderResponse toResponse(ServiceProviderModel model);
}

