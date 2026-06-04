package com.adarsh.employeemanagement.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notification {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String message;

    private boolean isRead;

    private LocalDateTime createdAt;

    public Notification() {

        this.createdAt =
                LocalDateTime.now();

        this.isRead = false;
    }

    public int getId() {

        return id;
    }

    public void setId(
            int id) {

        this.id = id;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(
            String username) {

        this.username = username;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(
            String message) {

        this.message = message;
    }

    public boolean isRead() {

        return isRead;
    }

    public void setRead(
            boolean read) {

        isRead = read;
    }

    public LocalDateTime getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt) {

        this.createdAt = createdAt;
    }
}