package com.adarsh.employeemanagement.dto;

public class ManagerAnalyticsResponse {

    private int managerId;

    private String managerName;

    private long tasksAssigned;

    private long completedTasks;

    private long pendingTasks;

    private long inProgressTasks;

    private long overdueTasks;

    private double completionPercentage;
    
    private int employeesUnderManager;

    private String performance;

    public ManagerAnalyticsResponse() {

    }

    public int getManagerId() {

        return managerId;
    }

    public void setManagerId(
            int managerId) {

        this.managerId = managerId;
    }

    public String getManagerName() {

        return managerName;
    }

    public void setManagerName(
            String managerName) {

        this.managerName = managerName;
    }

    public long getTasksAssigned() {

        return tasksAssigned;
    }

    public void setTasksAssigned(
            long tasksAssigned) {

        this.tasksAssigned = tasksAssigned;
    }

    public long getCompletedTasks() {

        return completedTasks;
    }

    public void setCompletedTasks(
            long completedTasks) {

        this.completedTasks = completedTasks;
    }

    public long getPendingTasks() {

        return pendingTasks;
    }

    public void setPendingTasks(
            long pendingTasks) {

        this.pendingTasks = pendingTasks;
    }

    public long getInProgressTasks() {

        return inProgressTasks;
    }

    public void setInProgressTasks(
            long inProgressTasks) {

        this.inProgressTasks = inProgressTasks;
    }

    public long getOverdueTasks() {

        return overdueTasks;
    }

    public void setOverdueTasks(
            long overdueTasks) {

        this.overdueTasks = overdueTasks;
    }

    public double getCompletionPercentage() {

        return completionPercentage;
    }

    public void setCompletionPercentage(
            double completionPercentage) {

        this.completionPercentage =
                completionPercentage;
    }
    
    public int getEmployeesUnderManager() {

        return employeesUnderManager;
    }

    public void setEmployeesUnderManager(
            int employeesUnderManager) {

        this.employeesUnderManager =
                employeesUnderManager;
    }

    public String getPerformance() {

        return performance;
    }

    public void setPerformance(
            String performance) {

        this.performance =
                performance;
    }
}