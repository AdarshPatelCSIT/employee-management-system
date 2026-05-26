package com.adarsh.employeemanagement.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.employeemanagement.model.Task;

public interface TaskRepository
       extends JpaRepository<Task, Integer> {

    List<Task> findByAssignedEmployeeId(
            int employeeId);

    List<Task>
    findByAssignedEmployeeIdAndStatus(
            int employeeId,
            String status);

    List<Task>
    findByAssignedEmployeeIdAndCompletedAtAfter(
            int employeeId,
            LocalDateTime dateTime);

    List<Task>
    findByAssignedEmployeeIdAndCompletedAtBetween(
            int employeeId,
            LocalDateTime startDate,
            LocalDateTime endDate);
}