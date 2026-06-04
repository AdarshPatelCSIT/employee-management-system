package com.adarsh.employeemanagement.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Project {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    private String clientName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<Task> tasks;

    public Project() {

        this.createdAt =
                LocalDateTime.now();
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
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

    public LocalDateTime getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt) {

        this.createdAt =
                createdAt;
    }

    public List<Task> getTasks() {

        return tasks;
    }

    public void setTasks(
            List<Task> tasks) {

        this.tasks = tasks;
    }
}