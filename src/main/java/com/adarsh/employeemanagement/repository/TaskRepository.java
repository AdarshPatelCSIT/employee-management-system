package com.adarsh.employeemanagement.repository;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.employeemanagement.model.Task;
import com.adarsh.employeemanagement.model.enums.TaskStatus;

public interface TaskRepository
       extends JpaRepository<Task, Integer> {

    List<Task> findByAssignedEmployeeId(
            int employeeId);

    List<Task>
    findByAssignedEmployeeIdAndStatus(
            int employeeId,
            TaskStatus status);
    
    List<Task>
    findByDueDateBeforeAndStatusNot(
            LocalDate date,
            TaskStatus status);
    
    List<Task> findByProjectId(
            int projectId);

    List<Task>
    findByAssignedEmployeeIdAndCompletedAtAfter(
            int employeeId,
            LocalDateTime dateTime);
    
    Page<Task>
    findByAssignedEmployeeIdAndTitleContainingIgnoreCase(
            int employeeId,
            String title,
            Pageable pageable);
    
    Page<Task>
    findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable);

    List<Task>
    findByAssignedEmployeeIdAndCompletedAtBetween(
            int employeeId,
            LocalDateTime startDate,
            LocalDateTime endDate);
    
    
    
    long countByStatus(
            TaskStatus status);
    
    long countByDueDateBeforeAndStatusNot(
            LocalDate date,
            TaskStatus status);
}