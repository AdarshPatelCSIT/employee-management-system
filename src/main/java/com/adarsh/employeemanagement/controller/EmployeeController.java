package com.adarsh.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.model.Employee;

@RestController
public class EmployeeController {

    List<Employee> employees = new ArrayList<>();

    public EmployeeController() {

        employees.add(new Employee(1, "Adarsh", "IT"));
        employees.add(new Employee(2, "Rahul", "HR"));
        employees.add(new Employee(3, "Amit", "Finance"));
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {

        return employees;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {

        employees.add(employee);

        return employee;
    }
    
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id,
                                   @RequestBody Employee updatedEmployee) {

        for (Employee employee : employees) {

            if (employee.getId() == id) {

                employee.setName(updatedEmployee.getName());
                employee.setDepartment(updatedEmployee.getDepartment());

                return employee;
            }
        }

        return null;
    }
    
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {

        for (Employee employee : employees) {

            if (employee.getId() == id) {

                employees.remove(employee);

                return "Employee deleted successfully";
            }
        }

        return "Employee not found";
    }
}  