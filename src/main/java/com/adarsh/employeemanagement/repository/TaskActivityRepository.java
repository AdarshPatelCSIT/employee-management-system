package com.adarsh.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.employeemanagement.model.TaskActivity;

public interface TaskActivityRepository
       extends JpaRepository<TaskActivity, Integer> {

    List<TaskActivity>
    findByTaskIdOrderByActivityTimeDesc(
            int taskId);
}