package com.adarsh.employeemanagement.dto;

import java.time.LocalDateTime;

public class AttachmentResponse {

    private String fileName;

    private String fileType;

    private String uploadedBy;

    private LocalDateTime uploadedAt;

    public AttachmentResponse() {

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

    public String getUploadedBy() {

        return uploadedBy;
    }

    public void setUploadedBy(
            String uploadedBy) {

        this.uploadedBy =
                uploadedBy;
    }

    public LocalDateTime getUploadedAt() {

        return uploadedAt;
    }

    public void setUploadedAt(
            LocalDateTime uploadedAt) {

        this.uploadedAt =
                uploadedAt;
    }
}