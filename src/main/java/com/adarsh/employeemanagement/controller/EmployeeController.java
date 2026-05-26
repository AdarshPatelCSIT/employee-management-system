package com.adarsh.employeemanagement.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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