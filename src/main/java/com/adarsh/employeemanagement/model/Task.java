package com.adarsh.employeemanagement.model;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private String status;

    private LocalDateTime assignedAt;

    private LocalDateTime updatedAt;

    private LocalDateTime completedAt;

    @ManyToOne
    private Employee assignedEmployee;

    @ManyToOne
    private User assignedBy;

    public Task() {

        this.status = "PENDING";

        this.assignedAt =
                LocalDateTime.now();
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
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

        this.description =
                description;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(
            String status) {

        this.status = status;

        this.updatedAt =
                LocalDateTime.now();

        if ("COMPLETED".equals(status)) {

            this.completedAt =
                    LocalDateTime.now();
        }
    }

    public LocalDateTime getAssignedAt() {

        return assignedAt;
    }

    public void setAssignedAt(
            LocalDateTime assignedAt) {

        this.assignedAt =
                assignedAt;
    }

    public LocalDateTime getUpdatedAt() {

        return updatedAt;
    }

    public void setUpdatedAt(
            LocalDateTime updatedAt) {

        this.updatedAt =
                updatedAt;
    }

    public LocalDateTime getCompletedAt() {

        return completedAt;
    }

    public void setCompletedAt(
            LocalDateTime completedAt) {

        this.completedAt =
                completedAt;
    }

    public Employee getAssignedEmployee() {

        return assignedEmployee;
    }

    public void setAssignedEmployee(
            Employee assignedEmployee) {

        this.assignedEmployee =
                assignedEmployee;
    }

    public User getAssignedBy() {

        return assignedBy;
    }

    public void setAssignedBy(
            User assignedBy) {

        this.assignedBy =
                assignedBy;
    }

    public String getTaskDuration() {

        if (completedAt == null) {

            return "Task not completed";
        }

        Duration duration =
                Duration.between(
                        assignedAt,
                        completedAt);

        long hours =
                duration.toHours();

        long minutes =
                duration.toMinutesPart();

        return hours
                + " hours "
                + minutes
                + " minutes";
    }
}