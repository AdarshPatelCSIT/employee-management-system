package com.adarsh.employeemanagement.dto;

import java.time.LocalDateTime;

public class CommentResponse {

    private String comment;

    private String commentedBy;

    private LocalDateTime commentedAt;

    public CommentResponse() {

    }

    public String getComment() {

        return comment;
    }

    public void setComment(
            String comment) {

        this.comment = comment;
    }

    public String getCommentedBy() {

        return commentedBy;
    }

    public void setCommentedBy(
            String commentedBy) {

        this.commentedBy =
                commentedBy;
    }

    public LocalDateTime getCommentedAt() {

        return commentedAt;
    }

    public void setCommentedAt(
            LocalDateTime commentedAt) {

        this.commentedAt =
                commentedAt;
    }
}