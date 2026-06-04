package com.adarsh.employeemanagement.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TaskActivity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private int id;

    private String action;

    private LocalDateTime activityTime;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User performedBy;

    public TaskActivity() {

        this.activityTime =
                LocalDateTime.now();
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getAction() {

        return action;
    }

    public void setAction(
            String action) {

        this.action = action;
    }

    public LocalDateTime getActivityTime() {

        return activityTime;
    }

    public void setActivityTime(
            LocalDateTime activityTime) {

        this.activityTime =
                activityTime;
    }

    public Task getTask() {

        return task;
    }

    public void setTask(
            Task task) {

        this.task = task;
    }

    public User getPerformedBy() {

        return performedBy;
    }

    public void setPerformedBy(
            User performedBy) {

        this.performedBy =
                performedBy;
    }
}