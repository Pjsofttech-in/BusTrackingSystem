package com.app.bustracking.controller;

import com.app.bustracking.Response.DashboardResponse;
import com.app.bustracking.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboard() {
        return dashboardService.getDashboardData();
    }
}