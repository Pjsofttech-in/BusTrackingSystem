package com.app.bustracking.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "student_fee_payment")
public class StudentFeePaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    StudentModel student;

    Double amount;
    String paymentMode;      // CASH, ONLINE, UPI, CARD
    String status;           // SUCCESS, PENDING, FAILED

    LocalDate paymentDate;
    LocalDateTime paymentDateTime;
    LocalDate dueDate;

    String transactionId;

    @PrePersist
    protected void onCreate() {
        paymentDateTime = LocalDateTime.now();
        if (paymentDate == null) paymentDate = LocalDate.now();
    }
}