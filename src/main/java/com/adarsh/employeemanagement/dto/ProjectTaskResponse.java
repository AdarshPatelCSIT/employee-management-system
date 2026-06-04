package com.adarsh.employeemanagement.dto;

import java.time.LocalDate;

import com.adarsh.employeemanagement.model.enums.TaskPriority;
import com.adarsh.employeemanagement.model.enums.TaskStatus;

public class ProjectTaskResponse {

    private int taskId;

    private String title;

    private String employeeName;

    private String managerName;

    private TaskStatus status;

    private TaskPriority priority;

    private LocalDate dueDate;

    private String taskDuration;

    public ProjectTaskResponse() {

    }

    // Generate getters/setters
    
    public int getTaskId() {

        return taskId;
    }

    public void setTaskId(
            int taskId) {

        this.taskId = taskId;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(
            String title) {

        this.title = title;
    }

    public String getEmployeeName() {

        return employeeName;
    }

    public void setEmployeeName(
            String employeeName) {

        this.employeeName =
                employeeName;
    }

    public String getManagerName() {

        return managerName;
    }

    public void setManagerName(
            String managerName) {

        this.managerName =
                managerName;
    }

    public TaskStatus getStatus() {

        return status;
    }

    public void setStatus(
            TaskStatus status) {

        this.status = status;
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

    public String getTaskDuration() {

        return taskDuration;
    }

    public void setTaskDuration(
            String taskDuration) {

        this.taskDuration =
                taskDuration;
    }
}