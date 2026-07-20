package com.app.bustracking.Mapper;

import com.app.bustracking.Model.StudentScanModel;
import com.app.bustracking.Request.StudentScanRequest;
import com.app.bustracking.Response.StudentScanResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentScanMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "bus", ignore = true)
    @Mapping(target = "scannedAt", ignore = true)
    StudentScanModel toModel(StudentScanRequest request);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "studentName", source = "student.name")
    @Mapping(target = "studentRollNumber", source = "student.rollNumber")
    @Mapping(target = "busId", source = "bus.id")
    @Mapping(target = "busNumber", source = "bus.busNumber")
    StudentScanResponse toResponse(StudentScanModel scan);
}