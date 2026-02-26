package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.StudentFeePaymentRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.StudentFeePaymentResponseDTO;
import com.bus.tracking.bus_tracking_system.service.StudentFeePaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fees")
@CrossOrigin(origins = "https://pjsofttech.com")
public class StudentFeePaymentController {

    private final StudentFeePaymentService service;

    public StudentFeePaymentController(StudentFeePaymentService service) {
        this.service = service;
    }

    // SIMPLE PAY (AUTO PAID)
    @PostMapping("/pay")
    public StudentFeePaymentResponseDTO pay(
            @RequestBody StudentFeePaymentRequestDTO dto) {

        return service.payFees(dto);
    }

    // FULL PROCESS (PAID / PENDING)
    @PostMapping("/process")
    public StudentFeePaymentResponseDTO process(
            @RequestBody StudentFeePaymentRequestDTO dto) {

        return service.processFees(dto);
    }
}