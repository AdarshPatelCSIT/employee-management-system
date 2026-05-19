package com.adarsh.employeemanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {

    @Id
    private int id;

    private String title;

    private String description;

    private String status;

    @ManyToOne
    private Employee employee;

    public Task() {

    }

    public Task(int id, String title, String description,
                String status, Employee employee) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}