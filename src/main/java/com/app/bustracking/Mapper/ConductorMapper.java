// src/main/java/com/app/bustracking/mapper/ConductorMapper.java
package com.app.bustracking.Mapper;

import com.app.bustracking.Model.ConductorModel;
import com.app.bustracking.Request.ConductorRequest;
import com.app.bustracking.Response.ConductorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConductorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ConductorModel toModel(ConductorRequest request);

    ConductorResponse toResponse(ConductorModel model);
}