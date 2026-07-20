package com.app.bustracking.service;

import com.app.bustracking.Request.StudentFeePaymentRequest;
import com.app.bustracking.Response.StudentFeePaymentResponse;

import java.util.List;

public interface StudentFeePaymentService {
    StudentFeePaymentResponse payFee(StudentFeePaymentRequest request);
    List<StudentFeePaymentResponse> getHistoryByStudentId(Long studentId);
    List<StudentFeePaymentResponse> getAll();
}