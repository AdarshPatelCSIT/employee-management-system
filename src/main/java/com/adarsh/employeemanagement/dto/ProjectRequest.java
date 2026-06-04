package com.adarsh.employeemanagement.dto;

import java.time.LocalDate;

public class ProjectRequest {

    private String name;

    private String description;

    private String clientName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    public ProjectRequest() {

    }

    public String getName() {

        return name;
    }

    public void setName(
            String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(
            String description) {

        this.description =
                description;
    }

    public String getClientName() {

        return clientName;
    }

    public void setClientName(
            String clientName) {

        this.clientName =
                clientName;
    }

    public LocalDate getStartDate() {

        return startDate;
    }

    public void setStartDate(
            LocalDate startDate) {

        this.startDate =
                startDate;
    }

    public LocalDate getEndDate() {

        return endDate;
    }

    public void setEndDate(
            LocalDate endDate) {

        this.endDate =
                endDate;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(
            String status) {

        this.status = status;
    }
}