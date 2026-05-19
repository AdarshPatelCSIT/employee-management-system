package com.adarsh.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.employeemanagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}