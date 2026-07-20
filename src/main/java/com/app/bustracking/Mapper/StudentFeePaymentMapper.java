package com.app.bustracking.Mapper;

import com.app.bustracking.Model.StudentFeePaymentModel;
import com.app.bustracking.Request.StudentFeePaymentRequest;
import com.app.bustracking.Response.StudentFeePaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentFeePaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "paymentDateTime", ignore = true)
    @Mapping(target = "paymentDate", expression = "java(java.time.LocalDate.now())")
    StudentFeePaymentModel toModel(StudentFeePaymentRequest request);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "studentName", source = "student.name")
    StudentFeePaymentResponse toResponse(StudentFeePaymentModel payment);
}