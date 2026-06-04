package com.adarsh.employeemanagement.dto;

public class ProjectDashboardResponse {

    private int projectId;

    private String projectName;

    private long totalTasks;

    private long completedTasks;

    private long pendingTasks;

    private long inProgressTasks;

    private long overdueTasks;

    private double completionPercentage;

    public ProjectDashboardResponse() {

    }

    public int getProjectId() {

        return projectId;
    }

    public void setProjectId(
            int projectId) {

        this.projectId = projectId;
    }

    public String getProjectName() {

        return projectName;
    }

    public void setProjectName(
            String projectName) {

        this.projectName =
                projectName;
    }

    public long getTotalTasks() {

        return totalTasks;
    }

    public void setTotalTasks(
            long totalTasks) {

        this.totalTasks =
                totalTasks;
    }

    public long getCompletedTasks() {

        return completedTasks;
    }

    public void setCompletedTasks(
            long completedTasks) {

        this.completedTasks =
                completedTasks;
    }

    public long getPendingTasks() {

        return pendingTasks;
    }

    public void setPendingTasks(
            long pendingTasks) {

        this.pendingTasks =
                pendingTasks;
    }

    public long getInProgressTasks() {

        return inProgressTasks;
    }

    public void setInProgressTasks(
            long inProgressTasks) {

        this.inProgressTasks =
                inProgressTasks;
    }

    public long getOverdueTasks() {

        return overdueTasks;
    }

    public void setOverdueTasks(
            long overdueTasks) {

        this.overdueTasks =
                overdueTasks;
    }

    public double getCompletionPercentage() {

        return completionPercentage;
    }

    public void setCompletionPercentage(
            double completionPercentage) {

        this.completionPercentage =
                completionPercentage;
    }
}