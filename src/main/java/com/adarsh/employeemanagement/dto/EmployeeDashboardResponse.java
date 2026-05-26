package com.adarsh.employeemanagement.dto;

import java.util.List;

public class EmployeeDashboardResponse {

    private String employeeName;

    private int totalTasks;

    private int completedTasks;

    private int pendingTasks;

    private int inProgressTasks;

    private List<TaskResponse> completedTaskList;

    private List<TaskResponse> pendingTaskList;

    private List<TaskResponse> inProgressTaskList;

    public EmployeeDashboardResponse() {

    }

    public String getEmployeeName() {

        return employeeName;
    }

    public void setEmployeeName(
            String employeeName) {

        this.employeeName =
                employeeName;
    }

    public int getTotalTasks() {

        return totalTasks;
    }

    public void setTotalTasks(
            int totalTasks) {

        this.totalTasks =
                totalTasks;
    }

    public int getCompletedTasks() {

        return completedTasks;
    }

    public void setCompletedTasks(
            int completedTasks) {

        this.completedTasks =
                completedTasks;
    }

    public int getPendingTasks() {

        return pendingTasks;
    }

    public void setPendingTasks(
            int pendingTasks) {

        this.pendingTasks =
                pendingTasks;
    }

    public int getInProgressTasks() {

        return inProgressTasks;
    }

    public void setInProgressTasks(
            int inProgressTasks) {

        this.inProgressTasks =
                inProgressTasks;
    }

    public List<TaskResponse>
           getCompletedTaskList() {

        return completedTaskList;
    }

    public void setCompletedTaskList(
            List<TaskResponse>
            completedTaskList) {

        this.completedTaskList =
                completedTaskList;
    }

    public List<TaskResponse>
           getPendingTaskList() {

        return pendingTaskList;
    }

    public void setPendingTaskList(
            List<TaskResponse>
            pendingTaskList) {

        this.pendingTaskList =
                pendingTaskList;
    }

    public List<TaskResponse>
           getInProgressTaskList() {

        return inProgressTaskList;
    }

    public void setInProgressTaskList(
            List<TaskResponse>
            inProgressTaskList) {

        this.inProgressTaskList =
                inProgressTaskList;
    }
}