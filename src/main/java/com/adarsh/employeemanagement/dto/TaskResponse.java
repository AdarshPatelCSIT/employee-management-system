package com.adarsh.employeemanagement.dto;

import java.time.LocalDate;

import com.adarsh.employeemanagement.model.enums.TaskPriority;
import com.adarsh.employeemanagement.model.enums.TaskStatus;

public class TaskResponse {

	private String projectName;
	private String employeeName;
	private String managerName;
	
    private String title;

    private TaskStatus status;

    private String taskDuration;

    private TaskPriority priority;

    private LocalDate dueDate;

    public TaskResponse() {

    }

    public String getTitle() {

        return title;
    }

    public void setTitle(
            String title) {

        this.title = title;
    }

    public TaskStatus getStatus() {

        return status;
    }

    public void setStatus(
            TaskStatus status) {

        this.status = status;
    }

    public String getTaskDuration() {

        return taskDuration;
    }

    public void setTaskDuration(
            String taskDuration) {

        this.taskDuration =
                taskDuration;
    }

    public TaskPriority getPriority() {

        return priority;
    }

    public void setPriority(
            TaskPriority priority) {

        this.priority =
                priority;
    }
    
    

    public LocalDate getDueDate() {

        return dueDate;
    }

    public void setDueDate(
            LocalDate dueDate) {

        this.dueDate =
                dueDate;
    }
    
    public String getProjectName() {

        return projectName;
    }

    public void setProjectName(
            String projectName) {

        this.projectName = projectName;
    }

    public String getEmployeeName() {

        return employeeName;
    }

    public void setEmployeeName(
            String employeeName) {

        this.employeeName = employeeName;
    }

    public String getManagerName() {

        return managerName;
    }

    public void setManagerName(
            String managerName) {

        this.managerName = managerName;
    }
}