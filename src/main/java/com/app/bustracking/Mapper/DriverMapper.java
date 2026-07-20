package com.app.bustracking.Mapper;

import com.app.bustracking.Model.DriverModel;
import com.app.bustracking.Request.DriverRequest;
import com.app.bustracking.Response.DriverResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    DriverModel toModel(DriverRequest request);

    DriverResponse toResponse(DriverModel model);
}