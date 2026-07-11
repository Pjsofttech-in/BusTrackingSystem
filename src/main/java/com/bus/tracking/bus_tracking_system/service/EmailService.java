package com.bus.tracking.bus_tracking_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired(required = false)
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Your email methods with null checks
    public void sendEmail(String to, String subject, String body) {
        if (mailSender == null) {
            System.out.println("Email service disabled. Would send email to: " + to);
            return;
        }
        // Actual email sending logic
    }
}