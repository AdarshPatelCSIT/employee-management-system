package com.adarsh.employeemanagement.service;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.Comparator;

import com.adarsh.employeemanagement.dto.EmployeeRankingResponse;
import com.adarsh.employeemanagement.model.enums.RankingPeriod;

import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.dto.EmployeeResponse;
import com.adarsh.employeemanagement.exception.ResourceNotFoundException;
import com.adarsh.employeemanagement.model.Employee;
import com.adarsh.employeemanagement.repository.EmployeeRepository;

import java.time.LocalDate;

import com.adarsh.employeemanagement.dto.EmployeeAnalyticsResponse;
import com.adarsh.employeemanagement.model.Task;
import com.adarsh.employeemanagement.model.enums.TaskStatus;
import com.adarsh.employeemanagement.repository.TaskRepository;
import java.time.LocalDateTime;
import com.adarsh.employeemanagement.dto.EmployeeDateRangeAnalyticsResponse;
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    
    private TaskRepository
    taskRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            TaskRepository taskRepository) {

        this.employeeRepository =
                employeeRepository;

        this.taskRepository =
                taskRepository;
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
    
    public EmployeeAnalyticsResponse
    getEmployeeAnalytics(
    int employeeId) {

 Employee employee =
         employeeRepository
         .findById(employeeId)
         .orElseThrow(() ->
                 new RuntimeException(
                         "Employee not found"));

 List<Task> tasks =
         taskRepository
         .findByAssignedEmployeeId(
                 employeeId);

 long assignedTasks =
         tasks.size();

 long completedTasks =
         tasks.stream()
         .filter(task ->
                 task.getStatus()
                 == TaskStatus.COMPLETED)
         .count();

 long pendingTasks =
         tasks.stream()
         .filter(task ->
                 task.getStatus()
                 == TaskStatus.PENDING)
         .count();

 long inProgressTasks =
         tasks.stream()
         .filter(task ->
                 task.getStatus()
                 == TaskStatus.IN_PROGRESS)
         .count();

 long overdueTasks =
         tasks.stream()
         .filter(task ->
                 task.getDueDate() != null
                 &&
                 task.getDueDate()
                 .isBefore(
                         LocalDate.now())
                 &&
                 task.getStatus()
                 != TaskStatus.COMPLETED)
         .count();

 double completionPercentage = 0;

 if (assignedTasks > 0) {

     completionPercentage =
             ((double) completedTasks
                     / assignedTasks)
                     * 100;
 }

 String performance = "POOR";

 if (completionPercentage >= 90) {

     performance = "EXCELLENT";

 } else if (completionPercentage >= 70) {

     performance = "GOOD";

 } else if (completionPercentage >= 50) {

     performance = "AVERAGE";

 } else if (completionPercentage >= 30) {

     performance =
             "NEEDS_IMPROVEMENT";
 }

 EmployeeAnalyticsResponse
         response =
         new EmployeeAnalyticsResponse();

 response.setEmployeeId(
         employee.getId());

 response.setEmployeeName(
         employee.getName());

 response.setDepartment(
         employee.getDepartment());

 response.setAssignedTasks(
         assignedTasks);

 response.setCompletedTasks(
         completedTasks);

 response.setPendingTasks(
         pendingTasks);

 response.setInProgressTasks(
         inProgressTasks);

 response.setOverdueTasks(
         overdueTasks);

 response.setCompletionPercentage(
         Math.round(
                 completionPercentage
                 * 100.0)
                 / 100.0);

 response.setPerformance(
         performance);

 return response;
}
    
    public EmployeeDateRangeAnalyticsResponse
    getEmployeeAnalyticsByDateRange(
    int employeeId,
    LocalDate startDate,
    LocalDate endDate) {

 Employee employee =
         employeeRepository
         .findById(employeeId)
         .orElseThrow(() ->
                 new RuntimeException(
                         "Employee not found"));

 LocalDateTime start =
         startDate.atStartOfDay();

 LocalDateTime end =
         endDate.atTime(
                 23,
                 59,
                 59);

 long assignedTasks =
         taskRepository
         .findByAssignedEmployeeId(
                 employeeId)
         .size();

 long completedTasks =
         taskRepository
         .findByAssignedEmployeeIdAndCompletedAtBetween(
                 employeeId,
                 start,
                 end)
         .size();

 double completionPercentage = 0;

 if (assignedTasks > 0) {

     completionPercentage =
             ((double)
             completedTasks
             / assignedTasks)
             * 100;
 }

 String performance = "POOR";

 if (completionPercentage >= 90) {

     performance = "EXCELLENT";

 } else if (
         completionPercentage >= 70) {

     performance = "GOOD";

 } else if (
         completionPercentage >= 50) {

     performance = "AVERAGE";

 } else if (
         completionPercentage >= 30) {

     performance =
             "NEEDS_IMPROVEMENT";
 }

 EmployeeDateRangeAnalyticsResponse
         response =
         new EmployeeDateRangeAnalyticsResponse();

 response.setEmployeeId(
         employee.getId());

 response.setEmployeeName(
         employee.getName());

 response.setDepartment(
         employee.getDepartment());

 response.setFromDate(
         startDate.toString());

 response.setToDate(
         endDate.toString());

 response.setAssignedTasks(
         assignedTasks);

 response.setCompletedTasks(
         completedTasks);

 response.setCompletionPercentage(
         Math.round(
                 completionPercentage
                 * 100.0)
                 / 100.0);

 response.setPerformance(
         performance);

 return response;
}

    public List<EmployeeRankingResponse>
    getEmployeeRanking(
    RankingPeriod period) {

 LocalDateTime startDate;

 LocalDateTime endDate =
         LocalDateTime.now();

 switch (period) {

     case DAILY:

         startDate =
                 LocalDateTime.now()
                 .minusDays(1);

         break;

     case WEEKLY:

         startDate =
                 LocalDateTime.now()
                 .minusWeeks(1);

         break;

     default:

         startDate =
                 LocalDateTime.now()
                 .minusMonths(1);
 }

 List<Employee> employees =
         employeeRepository.findAll();

 List<EmployeeRankingResponse>
         rankings =
         new java.util.ArrayList<>();

 for (Employee employee : employees) {

     List<Task> completedTasks =
             taskRepository
             .findByAssignedEmployeeIdAndCompletedAtBetween(
                     employee.getId(),
                     startDate,
                     endDate);

     long completedCount =
             completedTasks.size();

     long totalAssigned =
             taskRepository
             .findByAssignedEmployeeId(
                     employee.getId())
             .size();

     double completionPercentage = 0;

     if (totalAssigned > 0) {

         completionPercentage =
                 ((double)
                 completedCount
                 / totalAssigned)
                 * 100;
     }

     String performance =
             "POOR";

     if (completionPercentage >= 90) {

         performance =
                 "EXCELLENT";

     } else if (
             completionPercentage >= 70) {

         performance =
                 "GOOD";

     } else if (
             completionPercentage >= 50) {

         performance =
                 "AVERAGE";

     } else if (
             completionPercentage >= 30) {

         performance =
                 "NEEDS_IMPROVEMENT";
     }

     EmployeeRankingResponse
             response =
             new EmployeeRankingResponse();

     response.setEmployeeId(
             employee.getId());

     response.setEmployeeName(
             employee.getName());

     response.setDepartment(
             employee.getDepartment());

     if (employee.getManager()
             != null) {

         response.setManagerName(
                 employee.getManager()
                 .getUsername());
     }

     response.setCompletedTasks(
             completedCount);

     response.setCompletionPercentage(
             Math.round(
                     completionPercentage
                     * 100.0)
                     / 100.0);

     response.setPerformance(
             performance);

     response.setPeriod(
             period.name());

     rankings.add(
             response);
 }

 rankings.sort(
         Comparator.comparingLong(
                 EmployeeRankingResponse
                 ::getCompletedTasks)
         .reversed());

 for (int i = 0;
      i < rankings.size();
      i++) {

     rankings.get(i)
     .setRank(i + 1);
 }

 return rankings;
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