package com.adarsh.employeemanagement.controller;

import java.nio.file.Files;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.adarsh.employeemanagement.dto.ActivityResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import com.adarsh.employeemanagement.dto.AttachmentResponse;
import org.springframework.data.domain.Page;
import com.adarsh.employeemanagement.model.enums.TaskStatus;
import java.time.LocalDate;
import java.util.List;
import com.adarsh.employeemanagement.dto.OverdueTaskResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import com.adarsh.employeemanagement.dto.CommentResponse;
import org.springframework.http.ResponseEntity;
import com.adarsh.employeemanagement.dto.CommentRequest;
import com.adarsh.employeemanagement.model.TaskComment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.dto.DashboardResponse;
import com.adarsh.employeemanagement.dto.EmployeeDashboardResponse;
import com.adarsh.employeemanagement.dto.TaskRequest;
import com.adarsh.employeemanagement.dto.TaskResponse;
import com.adarsh.employeemanagement.dto.TaskStatusRequest;
import com.adarsh.employeemanagement.model.Task;
import com.adarsh.employeemanagement.model.enums.TaskStatus;
import com.adarsh.employeemanagement.service.TaskService;

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
    public ResponseEntity<TaskResponse>
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
           @RequestParam TaskStatus status) {

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

           @RequestParam TaskStatus status) {

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
    
    @GetMapping("/manager/overdue-tasks")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<List<OverdueTaskResponse>>
           getOverdueTasks() {

        return ResponseEntity.ok(
                taskService.getOverdueTasks());
    }
    
    @GetMapping("/tasks/search")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Page<TaskResponse>>
           searchTasks(

           @RequestParam String keyword,

           @RequestParam(defaultValue = "0")
           int page,

           @RequestParam(defaultValue = "5")
           int size,

           @RequestParam(defaultValue = "assignedAt")
           String sortBy) {

        return ResponseEntity.ok(
                taskService.searchTasks(
                        keyword,
                        page,
                        size,
                        sortBy));
    }
    
    
    @GetMapping("/my-tasks/search")
    @PreAuthorize(
        "hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<Page<TaskResponse>>
           searchMyTasks(

           @RequestParam String keyword,

           @RequestParam(defaultValue = "0")
           int page,

           @RequestParam(defaultValue = "5")
           int size,

           @RequestParam(defaultValue = "assignedAt")
           String sortBy) {

        return ResponseEntity.ok(
                taskService.searchMyTasks(
                        keyword,
                        page,
                        size,
                        sortBy));
    }

    @PostMapping("/tasks/{taskId}/comments")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_MANAGER', 'ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<CommentResponse>
           addComment(

           @PathVariable int taskId,

           @RequestBody
           CommentRequest request) {

        return ResponseEntity.ok(
                taskService.addComment(
                        taskId,
                        request));
    }
    
    @GetMapping("/tasks/{taskId}/comments")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_MANAGER', 'ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<List<CommentResponse>>
           getTaskComments(
           @PathVariable int taskId) {

        return ResponseEntity.ok(
                taskService
                .getTaskComments(taskId));
    }
    
    @PostMapping(
    	    value = "/tasks/{taskId}/attachments",
    	    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize(
        "hasAnyAuthority('ROLE_MANAGER', 'ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<AttachmentResponse>
           uploadAttachment(

           @PathVariable int taskId,

           @RequestParam("file")
           MultipartFile file)
           throws IOException {

        return ResponseEntity.ok(
                taskService.uploadAttachment(
                        taskId,
                        file));
    }
    
    @GetMapping("/tasks/{taskId}/attachments")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_MANAGER', 'ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<List<AttachmentResponse>>
           getTaskAttachments(
           @PathVariable int taskId) {

        return ResponseEntity.ok(
                taskService
                .getTaskAttachments(taskId));
    }
    
    @GetMapping(
    	    "/tasks/attachments/download/{fileName}")
    	@PreAuthorize(
    	    "hasAnyAuthority('ROLE_MANAGER', 'ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    	public ResponseEntity<Resource>
    	       downloadAttachment(
    	       @PathVariable String fileName)
    	       throws IOException {

    	    Path path =
    	            Paths.get("uploads")
    	                    .resolve(fileName)
    	                    .normalize();

    	    Resource resource =
    	            new UrlResource(
    	                    path.toUri());

    	    if (!resource.exists()) {

    	        throw new RuntimeException(
    	                "File not found");
    	    }

    	    String contentType =
    	            Files.probeContentType(
    	                    path);

    	    if (contentType == null) {

    	        contentType =
    	                "application/octet-stream";
    	    }

    	    return ResponseEntity.ok()
    	            .contentType(
    	                MediaType.parseMediaType(
    	                        contentType))
    	            .header(
    	                HttpHeaders.CONTENT_DISPOSITION,
    	                "inline; filename=\""
    	                + resource.getFilename()
    	                + "\"")
    	            .body(resource);
    	}
    
    @GetMapping("/tasks/{taskId}/activities")
    @PreAuthorize(
        "hasAnyAuthority('ROLE_MANAGER', 'ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public ResponseEntity<List<ActivityResponse>>
           getTaskActivities(
           @PathVariable int taskId) {

        return ResponseEntity.ok(
                taskService
                .getTaskActivities(taskId));
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