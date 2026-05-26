package com.adarsh.employeemanagement.dto;

public class TaskRequest {

    private String title;

    private String description;

    private String status;

    private int employeeId;

    public TaskRequest() {
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(
            String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(
            String description) {

        this.description = description;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(
            String status) {

        this.status = status;
    }

    public int getEmployeeId() {

        return employeeId;
    }

    public void setEmployeeId(
            int employeeId) {

        this.employeeId = employeeId;
    }
}