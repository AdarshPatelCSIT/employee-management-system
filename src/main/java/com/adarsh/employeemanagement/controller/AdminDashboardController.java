package com.adarsh.employeemanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.dto.AdminDashboardResponse;
import com.adarsh.employeemanagement.service.AdminDashboardService;

@RestController
public class AdminDashboardController {

    private AdminDashboardService
            adminDashboardService;

    public AdminDashboardController(
            AdminDashboardService
            adminDashboardService) {

        this.adminDashboardService =
                adminDashboardService;
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize(
        "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<
           AdminDashboardResponse>
           getDashboard() {

        return ResponseEntity.ok(
                adminDashboardService
                .getDashboard());
    }
}