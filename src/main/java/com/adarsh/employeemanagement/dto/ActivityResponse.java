package com.adarsh.employeemanagement.dto;

import java.time.LocalDateTime;

public class ActivityResponse {

    private String action;

    private String performedBy;

    private LocalDateTime activityTime;

    public ActivityResponse() {

    }

    public String getAction() {

        return action;
    }

    public void setAction(
            String action) {

        this.action = action;
    }

    public String getPerformedBy() {

        return performedBy;
    }

    public void setPerformedBy(
            String performedBy) {

        this.performedBy =
                performedBy;
    }

    public LocalDateTime getActivityTime() {

        return activityTime;
    }

    public void setActivityTime(
            LocalDateTime activityTime) {

        this.activityTime =
                activityTime;
    }
}