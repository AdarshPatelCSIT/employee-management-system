package com.adarsh.employeemanagement.service;

import com.adarsh.employeemanagement.model.Employee;
import com.adarsh.employeemanagement.repository.EmployeeRepository;
import com.adarsh.employeemanagement.dto.ManagerDetailResponse;
import com.adarsh.employeemanagement.dto.ManagerEmployeeResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.dto.ManagerAnalyticsResponse;
import com.adarsh.employeemanagement.model.Task;
import com.adarsh.employeemanagement.model.User;
import com.adarsh.employeemanagement.model.enums.TaskStatus;
import com.adarsh.employeemanagement.repository.TaskRepository;
import com.adarsh.employeemanagement.repository.UserRepository;

@Service
public class ManagerAnalyticsService {

    private UserRepository userRepository;

    private TaskRepository taskRepository;
    
    private EmployeeRepository
    employeeRepository;

    public ManagerAnalyticsService(
            UserRepository userRepository,
            TaskRepository taskRepository,
            EmployeeRepository employeeRepository) {

        this.userRepository =
                userRepository;

        this.taskRepository =
                taskRepository;
        
        this.employeeRepository =
                employeeRepository;
    }

    public List<ManagerAnalyticsResponse>
           getManagerAnalytics() {

        List<User> managers =
                userRepository.findAll()
                .stream()
                .filter(user ->
                        "MANAGER".equals(
                                user.getRole()))
                .toList();

        List<Task> allTasks =
                taskRepository.findAll();

        List<ManagerAnalyticsResponse>
                responses =
                new ArrayList<>();

        for (User manager : managers) {

            responses.add(
                    buildManagerResponse(
                            manager,
                            allTasks));
        }

        return responses;}
        
        private ManagerAnalyticsResponse
        buildManagerResponse(
        User manager,
        List<Task> tasks) {

    List<Task> managerTasks =
            tasks.stream()
                 .filter(task ->
                         task.getAssignedBy()
                         != null
                         &&
                         task.getAssignedBy()
                         .getId()
                         == manager.getId())
                 .toList();

    long totalTasks =
            managerTasks.size();

    long completedTasks =
            managerTasks.stream()
                        .filter(task ->
                                task.getStatus()
                                == TaskStatus.COMPLETED)
                        .count();

    long pendingTasks =
            managerTasks.stream()
                        .filter(task ->
                                task.getStatus()
                                == TaskStatus.PENDING)
                        .count();

    long inProgressTasks =
            managerTasks.stream()
                        .filter(task ->
                                task.getStatus()
                                == TaskStatus.IN_PROGRESS)
                        .count();

    long overdueTasks =
            managerTasks.stream()
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

    if (totalTasks > 0) {

        completionPercentage =
                ((double) completedTasks
                        / totalTasks)
                        * 100;
    }

    ManagerAnalyticsResponse
            response =
            new ManagerAnalyticsResponse();

    response.setManagerId(
            manager.getId());

    response.setManagerName(
            manager.getUsername());

    response.setTasksAssigned(
            totalTasks);

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
                    completionPercentage * 100.0)
                    / 100.0);
    
    String performance = "LOW";

    if (completionPercentage >= 90) {

        performance = "EXCELLENT";

    } else if (completionPercentage >= 70) {

        performance = "GOOD";

    } else if (completionPercentage >= 50) {

        performance = "AVERAGE";
    }

    response.setPerformance(
            performance);
   
    
    response.setEmployeesUnderManager(0);
    
    return response;

    }
        
        public ManagerDetailResponse
        getManagerDetails(
        int managerId) {

     User manager =
             userRepository
             .findById(managerId)
             .orElseThrow(() ->
                     new RuntimeException(
                             "Manager not found"));

     List<Employee> employees =
             employeeRepository
             .findByManagerId(
                     managerId);

     List<Task> managerTasks =
             taskRepository.findAll()
             .stream()
             .filter(task ->
                     task.getAssignedBy() != null
                     &&
                     task.getAssignedBy()
                     .getId() == managerId)
             .toList();
     long completedTasks =
    	        managerTasks.stream()
    	        .filter(task ->
    	                task.getStatus()
    	                == TaskStatus.COMPLETED)
    	        .count();

    	long pendingTasks =
    	        managerTasks.stream()
    	        .filter(task ->
    	                task.getStatus()
    	                == TaskStatus.PENDING)
    	        .count();

    	long inProgressTasks =
    	        managerTasks.stream()
    	        .filter(task ->
    	                task.getStatus()
    	                == TaskStatus.IN_PROGRESS)
    	        .count();

    	long overdueTasks =
    	        managerTasks.stream()
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

    	if (!managerTasks.isEmpty()) {

    	    completionPercentage =
    	            ((double) completedTasks
    	                    / managerTasks.size())
    	                    * 100;
    	}
    	String performance = "LOW";

    	if (completionPercentage >= 90) {

    	    performance = "EXCELLENT";

    	} else if (completionPercentage >= 70) {

    	    performance = "GOOD";

    	} else if (completionPercentage >= 50) {

    	    performance = "AVERAGE";
    	}

     ManagerDetailResponse response =
             new ManagerDetailResponse();

     response.setManagerId(
             manager.getId());

     response.setManagerName(
             manager.getUsername());

     response.setEmployeesUnderManager(
             employees.size());

     response.setTasksAssigned(
             managerTasks.size());
     
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
    	                completionPercentage * 100.0)
    	                / 100.0);

    	response.setPerformance(
    	        performance);

     List<ManagerEmployeeResponse>
             employeeResponses =
             new ArrayList<>();

     for (Employee employee : employees) {

         ManagerEmployeeResponse emp =
                 new ManagerEmployeeResponse();

         emp.setEmployeeId(
                 employee.getId());

         emp.setEmployeeName(
                 employee.getName());

         emp.setDepartment(
                 employee.getDepartment());

         employeeResponses.add(emp);
     }

     response.setEmployees(
             employeeResponses);

     return response;
 }
        
        
}