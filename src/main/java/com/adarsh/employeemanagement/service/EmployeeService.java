package com.adarsh.employeemanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.dto.EmployeeResponse;
import com.adarsh.employeemanagement.exception.ResourceNotFoundException;
import com.adarsh.employeemanagement.model.Employee;
import com.adarsh.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository) {

        this.employeeRepository =
                employeeRepository;
    }
    
    public List<EmployeeResponse>
    searchEmployees(
    String name) {

 List<Employee> employees =
         employeeRepository
         .findByNameContainingIgnoreCase(
                 name);

 return employees.stream()
         .map(employee -> {

             EmployeeResponse response =
                     new EmployeeResponse();

             response.setId(
                     employee.getId());

             response.setName(
                     employee.getName());

             response.setDepartment(
                     employee.getDepartment());

             return response;

         }).toList();
}

    public List<Employee> getEmployees() {

        return employeeRepository.findAll();
    }

    public List<EmployeeResponse>
           getAllEmployees() {

        List<Employee> employees =
                employeeRepository.findAll();

        return employees.stream()
                .map(employee -> {

                    EmployeeResponse response =
                            new EmployeeResponse();

                    response.setId(
                            employee.getId());

                    response.setName(
                            employee.getName());

                    response.setDepartment(
                            employee.getDepartment());

                    return response;

                }).collect(Collectors.toList());
    }

    public Employee addEmployee(
            Employee employee) {

        return employeeRepository.save(
                employee);
    }

    public Employee updateEmployee(
            int id,
            Employee updatedEmployee) {

        if (!employeeRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Employee not found with id "
                    + id);
        }

        updatedEmployee.setId(id);

        return employeeRepository.save(
                updatedEmployee);
    }

    public String deleteEmployee(
            int id) {

        if (!employeeRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Employee not found with id "
                    + id);
        }

        employeeRepository.deleteById(id);

        return "Employee deleted successfully";
    }
}