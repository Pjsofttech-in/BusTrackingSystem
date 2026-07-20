package com.app.bustracking.Mapper;

import com.app.bustracking.Model.DivisionModel;
import com.app.bustracking.Request.DivisionRequest;
import com.app.bustracking.Response.DivisionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DivisionMapper {

    @Mapping(target = "divisionId", ignore = true)
    DivisionModel toModel(DivisionRequest request);

    DivisionResponse toResponse(DivisionModel model);
}