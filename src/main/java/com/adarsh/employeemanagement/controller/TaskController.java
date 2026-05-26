package com.adarsh.employeemanagement.controller;

import com.adarsh.employeemanagement.dto.DashboardResponse;

import com.adarsh.employeemanagement.dto.EmployeeDashboardResponse;

import java.time.LocalDate;
import java.util.List;
import com.adarsh.employeemanagement.dto.TaskResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.dto.TaskRequest;
import com.adarsh.employeemanagement.dto.TaskStatusRequest;
import com.adarsh.employeemanagement.model.Task;
import com.adarsh.employeemanagement.service.TaskService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(
            TaskService taskService) {

        this.taskService =
                taskService;
    }

    @GetMapping("/tasks")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<List<Task>>
           getTasks() {

        return ResponseEntity.ok(
                taskService.getTasks());
    }

    @GetMapping("/my-tasks")
    @PreAuthorize(
        "hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<List<Task>>
           getMyTasks() {

        return ResponseEntity.ok(
                taskService.getMyTasks());
    }

    @GetMapping("/my-tasks/history")
    @PreAuthorize(
        "hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?>
           getMyTaskHistory(
           @RequestParam int days) {

        List<Task> tasks =
                taskService.getMyTaskHistory(
                        days);

        if (tasks.isEmpty()) {

            return ResponseEntity.ok(
                    "No task has been completed in selected duration");
        }

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/my-tasks/history/range")
    @PreAuthorize(
        "hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?>
           getMyTaskHistoryByRange(

           @Parameter(
               description =
               "Start date in format yyyy-MM-dd")
           @RequestParam
           @DateTimeFormat(
               pattern = "yyyy-MM-dd")
           @Schema(
               example = "2026-05-01")
           LocalDate startDate,

           @Parameter(
               description =
               "End date in format yyyy-MM-dd")
           @RequestParam
           @DateTimeFormat(
               pattern = "yyyy-MM-dd")
           @Schema(
               example = "2026-05-30")
           LocalDate endDate) {

        List<Task> tasks =
                taskService
                .getMyTaskHistoryByRange(
                        startDate,
                        endDate);

        if (tasks.isEmpty()) {

            return ResponseEntity.ok(
                    "No task completed in selected date range");
        }

        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/tasks")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Task>
           addTask(
           @RequestBody TaskRequest taskRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    taskService.addTask(
                            taskRequest));
    }

    @PutMapping("/tasks/{id}")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Task>
           updateTask(
           @PathVariable int id,
           @RequestBody Task updatedTask) {

        return ResponseEntity.ok(
                taskService.updateTask(
                        id,
                        updatedTask));
    }

    @PutMapping("/my-tasks/{id}/status")
    @PreAuthorize(
        "hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<Task>
           updateMyTaskStatus(
           @PathVariable int id,
           @RequestBody
           TaskStatusRequest request) {

        return ResponseEntity.ok(
                taskService.updateMyTaskStatus(
                        id,
                        request.getStatus()));
    }
    
    @GetMapping("/my-dashboard")
    @PreAuthorize(
        "hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<DashboardResponse>
           getMyDashboard() {

        return ResponseEntity.ok(
                taskService.getMyDashboard());
    }
    
    @GetMapping("/my-tasks/filter")
    @PreAuthorize(
        "hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?>
           getMyTasksByStatus(
           @RequestParam String status) {

        List<TaskResponse> tasks =
                taskService
                .getMyTasksByStatus(
                        status);

        if (tasks.isEmpty()) {

            return ResponseEntity.ok(
                    "No tasks found for status: "
                    + status);
        }

        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/manager/tasks/filter")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<?>
           getEmployeeTasksByStatus(

           @RequestParam int employeeId,

           @RequestParam String status) {

        List<TaskResponse> tasks =
                taskService
                .getEmployeeTasksByStatus(
                        employeeId,
                        status);

        if (tasks.isEmpty()) {

            return ResponseEntity.ok(
                    "No tasks found");
        }

        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/manager/employee-dashboard/{employeeId}")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<EmployeeDashboardResponse>
           getEmployeeDashboard(
           @PathVariable int employeeId) {

        return ResponseEntity.ok(
                taskService
                .getEmployeeDashboard(
                        employeeId));
    }

    @DeleteMapping("/tasks/{id}")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<String>
           deleteTask(
           @PathVariable int id) {

        return ResponseEntity.ok(
                taskService.deleteTask(id));
    }
}