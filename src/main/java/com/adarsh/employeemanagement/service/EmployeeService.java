package com.adarsh.employeemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.model.Employee;
import com.adarsh.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {

        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {

        updatedEmployee.setId(id);

        return employeeRepository.save(updatedEmployee);
    }

    public String deleteEmployee(int id) {

        employeeRepository.deleteById(id);

        return "Employee deleted successfully";
    }
}