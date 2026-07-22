package com.app.bustracking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "student_fee_payment")
public class StudentFeePaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentModel student;

    private Double amount;
    private String paymentMode;
    private String status;
    private LocalDate paymentDate;
    private LocalDateTime paymentDateTime;
    private LocalDate dueDate;
    private String transactionId;

    @PrePersist
    protected void onCreate() {
        paymentDateTime = LocalDateTime.now();
        if (paymentDate == null) paymentDate = LocalDate.now();
    }
}