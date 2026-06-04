package com.adarsh.employeemanagement.dto;

import com.adarsh.employeemanagement.model.enums.TaskStatus;

public class TaskStatusRequest {

    private TaskStatus status;

    public TaskStatusRequest() {

    }

    public TaskStatus getStatus() {

        return status;
    }

    public void setStatus(
            TaskStatus status) {

        this.status = status;
    }
}