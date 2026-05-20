package com.adarsh.employeemanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.model.Employee;
import com.adarsh.employeemanagement.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {

        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(
            @Valid @RequestBody Employee employee) {

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(employeeService.addEmployee(employee));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody Employee updatedEmployee) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, updatedEmployee));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable int id) {

        return ResponseEntity.ok(
                employeeService.deleteEmployee(id));
    }
}