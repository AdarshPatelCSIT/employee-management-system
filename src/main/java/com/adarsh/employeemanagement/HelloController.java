package com.adarsh.employeemanagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/employee")
    public Employee getEmployee() {

        Employee emp = new Employee(1, "Adarsh", "IT");

        return emp;
    }
}