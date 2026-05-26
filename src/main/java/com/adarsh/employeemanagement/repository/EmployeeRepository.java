package com.adarsh.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.employeemanagement.model.Employee;

public interface EmployeeRepository
       extends JpaRepository<Employee, Integer> {

    List<Employee>
    findByNameContainingIgnoreCase(
            String name);
}