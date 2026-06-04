package com.adarsh.employeemanagement.dto;

public class EmployeeRankingResponse {

    private int rank;

    private int employeeId;

    private String employeeName;

    private String department;

    private String managerName;

    private long completedTasks;

    private double completionPercentage;

    private String performance;

    private String period;

    public EmployeeRankingResponse() {
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public long getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(long completedTasks) {
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(
            String period) {

        this.period = period;
    }
}