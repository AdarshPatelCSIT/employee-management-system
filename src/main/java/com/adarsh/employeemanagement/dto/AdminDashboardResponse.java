package com.adarsh.employeemanagement.dto;

public class AdminDashboardResponse {

    private long totalEmployees;

    private long totalManagers;

    private long totalProjects;

    private long totalTasks;
    
    private long totalAdmins;

    private long completedTasks;

    private long pendingTasks;

    private long inProgressTasks;

    private long overdueTasks;

    public AdminDashboardResponse() {

    }
    
    public long getTotalEmployees() {

        return totalEmployees;
    }

    public void setTotalEmployees(
            long totalEmployees) {

        this.totalEmployees =
                totalEmployees;
    }

    public long getTotalManagers() {

        return totalManagers;
    }

    public void setTotalManagers(
            long totalManagers) {

        this.totalManagers =
                totalManagers;
    }

    public long getTotalAdmins() {

        return totalAdmins;
    }

    public void setTotalAdmins(
            long totalAdmins) {

        this.totalAdmins =
                totalAdmins;
    }
    
    public long getTotalProjects() {

        return totalProjects;
    }

    public void setTotalProjects(
            long totalProjects) {

        this.totalProjects =
                totalProjects;
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
}