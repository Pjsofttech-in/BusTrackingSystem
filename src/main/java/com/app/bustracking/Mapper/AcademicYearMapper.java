package com.app.bustracking.Mapper;

import com.app.bustracking.Model.AcademicYearModel;
import com.app.bustracking.Request.AcademicYearRequest;
import com.app.bustracking.Response.AcademicYearResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AcademicYearMapper {

    @Mapping(target = "id", ignore = true)
    AcademicYearModel toModel(AcademicYearRequest request);

    AcademicYearResponse toResponse(AcademicYearModel model);
}