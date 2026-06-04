package com.adarsh.employeemanagement.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.adarsh.employeemanagement.model.enums.TaskPriority;
import com.adarsh.employeemanagement.model.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    
    @ManyToOne
    private Project project;

    private LocalDate dueDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime assignedAt;

    private LocalDateTime updatedAt;

    private LocalDateTime completedAt;

    @ManyToOne
    private Employee assignedEmployee;

    @ManyToOne
    private User assignedBy;

    public Task() {

        this.status =
                TaskStatus.PENDING;

        this.assignedAt =
                LocalDateTime.now();
    }

    public int getId() {

        return id;
    }

    public void setId(
            int id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(
            String title) {

        this.title = title;
    }

    public LocalDate getDueDate() {

        return dueDate;
    }

    public void setDueDate(
            LocalDate dueDate) {

        this.dueDate =
                dueDate;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(
            String description) {

        this.description =
                description;
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

        this.status =
                status;

        this.updatedAt =
                LocalDateTime.now();

        if (status == TaskStatus.COMPLETED) {

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
    
    public Project getProject() {

        return project;
    }

    public void setProject(
            Project project) {

        this.project = project;
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