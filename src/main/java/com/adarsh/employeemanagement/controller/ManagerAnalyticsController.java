package com.adarsh.employeemanagement.controller;
import org.springframework.web.bind.annotation.PathVariable;
import com.adarsh.employeemanagement.dto.ManagerDetailResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.dto.ManagerAnalyticsResponse;
import com.adarsh.employeemanagement.service.ManagerAnalyticsService;

@RestController
public class ManagerAnalyticsController {

    private ManagerAnalyticsService
            managerAnalyticsService;

    public ManagerAnalyticsController(
            ManagerAnalyticsService
            managerAnalyticsService) {

        this.managerAnalyticsService =
                managerAnalyticsService;
    }

    @GetMapping(
            "/admin/managers/{managerId}")
    public ResponseEntity<
            ManagerDetailResponse>
            getManagerDetails(
            @PathVariable
            int managerId) {

        return ResponseEntity.ok(
                managerAnalyticsService
                .getManagerDetails(
                        managerId));
    }
    
    @GetMapping("/admin/managers")
    public ResponseEntity<
            List<ManagerAnalyticsResponse>>
            getManagers() {

        return ResponseEntity.ok(
                managerAnalyticsService
                .getManagerAnalytics());
    }
}