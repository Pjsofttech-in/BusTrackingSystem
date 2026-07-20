package com.app.bustracking.controller;

import com.app.bustracking.Request.StudentFeePaymentRequest;
import com.app.bustracking.Response.StudentFeePaymentResponse;
import com.app.bustracking.service.StudentFeePaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-fees")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class StudentFeePaymentController {

    private final StudentFeePaymentService service;

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentFeePaymentResponse payFee(@Valid @RequestBody StudentFeePaymentRequest request) {
        return service.payFee(request);
    }

    @GetMapping("/student/{studentId}")
    public List<StudentFeePaymentResponse> getHistory(@PathVariable Long studentId) {
        return service.getHistoryByStudentId(studentId);
    }

    @GetMapping
    public List<StudentFeePaymentResponse> getAll() {
        return service.getAll();
    }
}