package com.adarsh.employeemanagement.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TaskComment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;

    private LocalDateTime commentedAt;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User commentedBy;

    public TaskComment() {

        this.commentedAt =
                LocalDateTime.now();
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getComment() {

        return comment;
    }

    public void setComment(
            String comment) {

        this.comment = comment;
    }

    public LocalDateTime getCommentedAt() {

        return commentedAt;
    }

    public void setCommentedAt(
            LocalDateTime commentedAt) {

        this.commentedAt =
                commentedAt;
    }

    public Task getTask() {

        return task;
    }

    public void setTask(
            Task task) {

        this.task = task;
    }

    public User getCommentedBy() {

        return commentedBy;
    }

    public void setCommentedBy(
            User commentedBy) {

        this.commentedBy =
                commentedBy;
    }
}