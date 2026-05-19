package com.adarsh.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.employeemanagement.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}