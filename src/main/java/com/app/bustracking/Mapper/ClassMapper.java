package com.app.bustracking.Mapper;

import com.app.bustracking.Model.ClassModel;
import com.app.bustracking.Request.ClassRequest;
import com.app.bustracking.Response.ClassResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    @Mapping(target = "id", ignore = true)
    ClassModel toModel(ClassRequest request);

    ClassResponse toResponse(ClassModel model);
}