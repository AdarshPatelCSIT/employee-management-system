package com.adarsh.employeemanagement.dto;

public class EmployeeAnalyticsResponse {

    private int employeeId;

    private String employeeName;

    private String department;

    private long assignedTasks;

    private long completedTasks;

    private long pendingTasks;

    private long inProgressTasks;

    private long overdueTasks;

    private double completionPercentage;

    private String performance;

    public EmployeeAnalyticsResponse() {
    }

    public int getEmployeeId() {

        return employeeId;
    }

    public void setEmployeeId(
            int employeeId) {

        this.employeeId = employeeId;
    }

    public String getEmployeeName() {

        return employeeName;
    }

    public void setEmployeeName(
            String employeeName) {

        this.employeeName = employeeName;
    }

    public String getDepartment() {

        return department;
    }

    public void setDepartment(
            String department) {

        this.department = department;
    }

    public long getAssignedTasks() {

        return assignedTasks;
    }

    public void setAssignedTasks(
            long assignedTasks) {

        this.assignedTasks = assignedTasks;
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

        this.inProgressTasks =
                inProgressTasks;
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

    public String getPerformance() {

        return performance;
    }

    public void setPerformance(
            String performance) {

        this.performance = performance;
    }
}