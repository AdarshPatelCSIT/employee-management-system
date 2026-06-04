package com.adarsh.employeemanagement.service;
import com.adarsh.employeemanagement.dto.ProjectResponse;

import java.util.List;
import com.adarsh.employeemanagement.dto.ProjectTaskResponse;
import com.adarsh.employeemanagement.model.Task;
import com.adarsh.employeemanagement.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import com.adarsh.employeemanagement.dto.ProjectDashboardResponse;
import com.adarsh.employeemanagement.model.enums.TaskStatus;

import com.adarsh.employeemanagement.dto.ProjectRequest;
import com.adarsh.employeemanagement.model.Project;
import com.adarsh.employeemanagement.repository.ProjectRepository;

@Service
public class ProjectService {

    private ProjectRepository
            projectRepository;
    
    private TaskRepository
    taskRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            TaskRepository taskRepository) {

        this.projectRepository =
                projectRepository;
        
        this.taskRepository =
                taskRepository;
    }
    
    public List<ProjectTaskResponse>
    getProjectTasks(
    int projectId) {

 List<Task> tasks =
         taskRepository
         .findByProjectId(
                 projectId);

 return tasks.stream()
         .map(this::convertToProjectTaskResponse)
         .toList();
}
    
    private ProjectTaskResponse
    convertToProjectTaskResponse(
    Task task) {

ProjectTaskResponse response =
        new ProjectTaskResponse();

response.setTaskId(
        task.getId());

response.setTitle(
        task.getTitle());

response.setStatus(
        task.getStatus());

response.setPriority(
        task.getPriority());

response.setDueDate(
        task.getDueDate());

response.setTaskDuration(
        task.getTaskDuration());

if (task.getAssignedEmployee()
        != null) {

    response.setEmployeeName(
            task.getAssignedEmployee()
                .getName());
}

if (task.getAssignedBy()
        != null) {

    response.setManagerName(
            task.getAssignedBy()
                .getUsername());
}

return response;
}

    public ProjectResponse createProject(
            ProjectRequest request) {

        Project project =
                new Project();

        project.setName(
                request.getName());

        project.setDescription(
                request.getDescription());

        project.setClientName(
                request.getClientName());

        project.setStartDate(
                request.getStartDate());

        project.setEndDate(
                request.getEndDate());

        project.setStatus(
                request.getStatus());

        Project savedProject =
                projectRepository
                .save(project);

        return convertToResponse(
                savedProject);
    }
    
    private ProjectResponse
    convertToResponse(
    Project project) {

ProjectResponse response =
        new ProjectResponse();

response.setId(
        project.getId());

response.setName(
        project.getName());

response.setDescription(
        project.getDescription());

response.setClientName(
        project.getClientName());

response.setStartDate(
        project.getStartDate());

response.setEndDate(
        project.getEndDate());

response.setStatus(
        project.getStatus());

return response;
}

    public List<ProjectResponse>
    getAllProjects() {

    	return projectRepository
    	        .findAll()
    	        .stream()
    	        .map(this::convertToResponse)
    	        .toList();
    }
    
    public ProjectDashboardResponse
    getProjectDashboard(
    int projectId) {

 Project project =
         projectRepository
         .findById(projectId)
         .orElseThrow(
                 () -> new RuntimeException(
                         "Project not found"));

 List<Task> tasks =
         taskRepository
         .findByProjectId(
                 projectId);

 long totalTasks =
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

 ProjectDashboardResponse response =
         new ProjectDashboardResponse();

 response.setProjectId(
         project.getId());

 response.setProjectName(
         project.getName());

 response.setTotalTasks(
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
         completionPercentage);

 return response;
}
}