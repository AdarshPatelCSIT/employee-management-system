package com.adarsh.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.model.Employee;

@Service
public class EmployeeService {

    List<Employee> employees = new ArrayList<>();

    public EmployeeService() {

        employees.add(new Employee(1, "Adarsh", "IT"));
        employees.add(new Employee(2, "Rahul", "HR"));
        employees.add(new Employee(3, "Amit", "Finance"));
    }

    public List<Employee> getEmployees() {

        return employees;
    }
    
    public Employee addEmployee(Employee employee) {

        employees.add(employee);

        return employee;
    }
    
    public Employee updateEmployee(int id, Employee updatedEmployee) {

        for (Employee employee : employees) {

            if (employee.getId() == id) {

                employee.setName(updatedEmployee.getName());

                employee.setDepartment(updatedEmployee.getDepartment());

                return employee;
            }
        }

        return null;
    }
    
    public String deleteEmployee(int id) {

        for (Employee employee : employees) {

            if (employee.getId() == id) {

                employees.remove(employee);

                return "Employee deleted successfully";
            }
        }

        return "Employee not found";
    }
}