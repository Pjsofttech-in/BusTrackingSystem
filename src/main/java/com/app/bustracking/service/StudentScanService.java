package com.app.bustracking.service;

import com.app.bustracking.Request.StudentScanRequest;
import com.app.bustracking.Response.StudentScanResponse;

import java.util.List;

public interface StudentScanService {
    StudentScanResponse recordScan(StudentScanRequest request);
    List<StudentScanResponse> getByStudentId(Long studentId);
    List<StudentScanResponse> getByBusId(Long busId);
    List<StudentScanResponse> getAll();
}