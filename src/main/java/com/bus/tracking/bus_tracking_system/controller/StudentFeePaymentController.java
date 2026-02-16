package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.StudentFeePayment;
import com.bus.tracking.bus_tracking_system.service.StudentFeePaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fees")
@CrossOrigin
public class StudentFeePaymentController {

    private final StudentFeePaymentService service;

    public StudentFeePaymentController(StudentFeePaymentService service) {
        this.service = service;
    }

    @PostMapping("/pay/{studentId}")
    public StudentFeePayment pay(@PathVariable Long studentId,
                                 @RequestParam Double amount,
                                 @RequestParam String mode) {
        return service.payFees(studentId, amount, mode);
    }
}
