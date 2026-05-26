package com.adarsh.employeemanagement.dto;

public class TaskResponse {

    private String title;

    private String status;

    private String taskDuration;

    public TaskResponse() {

    }

    public String getTitle() {

        return title;
    }

    public void setTitle(
            String title) {

        this.title = title;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(
            String status) {

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
}