package com.adarsh.employeemanagement.controller;

import java.util.List;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.adarsh.employeemanagement.dto.EmployeeDateRangeAnalyticsResponse;
import org.springframework.web.bind.annotation.PathVariable;

import com.adarsh.employeemanagement.dto.EmployeeAnalyticsResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import com.adarsh.employeemanagement.dto.EmployeeRankingResponse;
import com.adarsh.employeemanagement.model.enums.RankingPeriod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.dto.EmployeeResponse;
import com.adarsh.employeemanagement.model.Employee;
import com.adarsh.employeemanagement.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(
            EmployeeService employeeService) {

        this.employeeService =
                employeeService;
    }

    @GetMapping("/employees")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<List<EmployeeResponse>>
           getEmployees() {

        return ResponseEntity.ok(
                employeeService.getAllEmployees());
    }

    @PostMapping("/employees")
    @PreAuthorize(
        "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Employee>
           addEmployee(
           @Valid
           @RequestBody Employee employee) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    employeeService
                    .addEmployee(employee));
    }

    @PutMapping("/employees/{id}")
    @PreAuthorize(
        "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Employee>
           updateEmployee(
           @PathVariable int id,

           @Valid
           @RequestBody
           Employee updatedEmployee) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(
                        id,
                        updatedEmployee));
    }
    
    @GetMapping("/employees/search")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<List<EmployeeResponse>>
           searchEmployees(
           @RequestParam String name) {

        return ResponseEntity.ok(
                employeeService
                .searchEmployees(name));
    }
    
    @GetMapping(
            "/admin/employees/{employeeId}/analytics")
    @PreAuthorize(
        "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<
           EmployeeAnalyticsResponse>
           getEmployeeAnalytics(
           @PathVariable
           int employeeId) {

        return ResponseEntity.ok(
                employeeService
                .getEmployeeAnalytics(
                        employeeId));
    }
    
    @GetMapping(
            "/admin/employees/ranking")
    @PreAuthorize(
        "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<
            List<EmployeeRankingResponse>>
            getEmployeeRanking(
            @RequestParam
            RankingPeriod period) {

        return ResponseEntity.ok(
                employeeService
                .getEmployeeRanking(
                        period));
    }
    
    @GetMapping(
            "/admin/employees/{employeeId}/analytics/date-range")
    @PreAuthorize(
        "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<
            EmployeeDateRangeAnalyticsResponse>
            getEmployeeAnalyticsByDateRange(

            @PathVariable
            int employeeId,

            @RequestParam
            @DateTimeFormat(
                iso =
                DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam
            @DateTimeFormat(
                iso =
                DateTimeFormat.ISO.DATE)
            LocalDate endDate) {

        return ResponseEntity.ok(
                employeeService
                .getEmployeeAnalyticsByDateRange(
                        employeeId,
                        startDate,
                        endDate));
    }
    


    @DeleteMapping("/employees/{id}")
    @PreAuthorize(
        "hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String>
           deleteEmployee(
           @PathVariable int id) {

        return ResponseEntity.ok(
                employeeService.deleteEmployee(
                        id));
    }
}