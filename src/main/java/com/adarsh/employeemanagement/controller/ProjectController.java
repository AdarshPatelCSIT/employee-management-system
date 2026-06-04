package com.adarsh.employeemanagement.controller;

import java.util.List;
import com.adarsh.employeemanagement.dto.ProjectTaskResponse;
import com.adarsh.employeemanagement.dto.ProjectResponse;
import org.springframework.web.bind.annotation.PathVariable;
import com.adarsh.employeemanagement.dto.ProjectDashboardResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.dto.ProjectRequest;
import com.adarsh.employeemanagement.model.Project;
import com.adarsh.employeemanagement.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService
            projectService;

    public ProjectController(
            ProjectService projectService) {

        this.projectService =
                projectService;
    }

    @PostMapping
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<ProjectResponse>
           createProject(
           @RequestBody
           ProjectRequest request) {

        return ResponseEntity.ok(
                projectService
                .createProject(request));
    }
    
    @GetMapping(
            "/projects/{projectId}/tasks")
    public ResponseEntity<
           List<ProjectTaskResponse>>
           getProjectTasks(

           @PathVariable
           int projectId) {

        return ResponseEntity.ok(
                projectService
                .getProjectTasks(
                        projectId));
    }
    @GetMapping(
            "/projects/{projectId}/dashboard")
    public ResponseEntity<
            ProjectDashboardResponse>
            getProjectDashboard(

            @PathVariable
            int projectId) {

        return ResponseEntity.ok(
                projectService
                .getProjectDashboard(
                        projectId));
    }

    @GetMapping
    @PreAuthorize(
        "hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_EMPLOYEE')")
    public ResponseEntity<List<ProjectResponse>>
           getAllProjects() {

        return ResponseEntity.ok(
                projectService
                .getAllProjects());
    }
}