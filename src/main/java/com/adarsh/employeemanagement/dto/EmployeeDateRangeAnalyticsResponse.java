package com.adarsh.employeemanagement.dto;

public class EmployeeDateRangeAnalyticsResponse {

    private int employeeId;

    private String employeeName;

    private String department;

    private String fromDate;

    private String toDate;

    private long assignedTasks;

    private long completedTasks;

    private double completionPercentage;

    private String performance;

    public EmployeeDateRangeAnalyticsResponse() {
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(
            String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(
            String toDate) {
        this.toDate = toDate;
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