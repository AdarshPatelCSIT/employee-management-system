package com.adarsh.employeemanagement.dto;

public class DashboardResponse {

    private int totalTasks;

    private int completedTasks;

    private int pendingTasks;

    private int inProgressTasks;

    public DashboardResponse() {

    }

    public int getTotalTasks() {

        return totalTasks;
    }

    public void setTotalTasks(
            int totalTasks) {

        this.totalTasks = totalTasks;
    }

    public int getCompletedTasks() {

        return completedTasks;
    }

    public void setCompletedTasks(
            int completedTasks) {

        this.completedTasks = completedTasks;
    }

    public int getPendingTasks() {

        return pendingTasks;
    }

    public void setPendingTasks(
            int pendingTasks) {

        this.pendingTasks = pendingTasks;
    }

    public int getInProgressTasks() {

        return inProgressTasks;
    }

    public void setInProgressTasks(
            int inProgressTasks) {

        this.inProgressTasks =
                inProgressTasks;
    }
}