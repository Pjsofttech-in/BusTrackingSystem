package com.app.bustracking.Mapper;

import com.app.bustracking.Model.MediumModel;
import com.app.bustracking.Request.MediumRequest;
import com.app.bustracking.Response.MediumResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MediumMapper {

    @Mapping(target = "id", ignore = true)
    MediumModel toModel(MediumRequest request);

    MediumResponse toResponse(MediumModel model);
}