package com.adarsh.employeemanagement.dto;

import java.time.LocalDate;

import com.adarsh.employeemanagement.model.enums.TaskPriority;
import com.adarsh.employeemanagement.model.enums.TaskStatus;

public class OverdueTaskResponse {

    private String employeeName;

    private String taskTitle;

    private TaskPriority priority;

    private TaskStatus status;

    private LocalDate dueDate;

    private long overdueDays;

    public OverdueTaskResponse() {

    }

    public String getEmployeeName() {

        return employeeName;
    }

    public void setEmployeeName(
            String employeeName) {

        this.employeeName =
                employeeName;
    }

    public String getTaskTitle() {

        return taskTitle;
    }

    public void setTaskTitle(
            String taskTitle) {

        this.taskTitle =
                taskTitle;
    }

    public TaskPriority getPriority() {

        return priority;
    }

    public void setPriority(
            TaskPriority priority) {

        this.priority =
                priority;
    }

    public TaskStatus getStatus() {

        return status;
    }

    public void setStatus(
            TaskStatus status) {

        this.status = status;
    }

    public LocalDate getDueDate() {

        return dueDate;
    }

    public void setDueDate(
            LocalDate dueDate) {

        this.dueDate =
                dueDate;
    }

    public long getOverdueDays() {

        return overdueDays;
    }

    public void setOverdueDays(
            long overdueDays) {

        this.overdueDays =
                overdueDays;
    }
}