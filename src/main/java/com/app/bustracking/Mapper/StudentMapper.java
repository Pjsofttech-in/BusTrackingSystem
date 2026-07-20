package com.app.bustracking.Mapper;

import com.app.bustracking.Model.StudentModel;
import com.app.bustracking.Request.StudentRequest;
import com.app.bustracking.Response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "payments", ignore = true)
    @Mapping(target = "scans", ignore = true)
    StudentModel toModel(StudentRequest request);

    StudentResponse toResponse(StudentModel model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "payments", ignore = true)
    @Mapping(target = "scans", ignore = true)
    void updateModel(@MappingTarget StudentModel model, StudentRequest request);
}