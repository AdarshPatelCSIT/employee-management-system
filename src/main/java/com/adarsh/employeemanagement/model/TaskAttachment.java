package com.adarsh.employeemanagement.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TaskAttachment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;

    private String fileType;

    private String filePath;

    private LocalDateTime uploadedAt;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User uploadedBy;

    public TaskAttachment() {

        this.uploadedAt =
                LocalDateTime.now();
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getFileName() {

        return fileName;
    }

    public void setFileName(
            String fileName) {

        this.fileName =
                fileName;
    }

    public String getFileType() {

        return fileType;
    }

    public void setFileType(
            String fileType) {

        this.fileType =
                fileType;
    }

    public String getFilePath() {

        return filePath;
    }

    public void setFilePath(
            String filePath) {

        this.filePath =
                filePath;
    }

    public LocalDateTime getUploadedAt() {

        return uploadedAt;
    }

    public void setUploadedAt(
            LocalDateTime uploadedAt) {

        this.uploadedAt =
                uploadedAt;
    }

    public Task getTask() {

        return task;
    }

    public void setTask(
            Task task) {

        this.task = task;
    }

    public User getUploadedBy() {

        return uploadedBy;
    }

    public void setUploadedBy(
            User uploadedBy) {

        this.uploadedBy =
                uploadedBy;
    }
}