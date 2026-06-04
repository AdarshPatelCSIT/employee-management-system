package com.adarsh.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.employeemanagement.model.TaskComment;

public interface TaskCommentRepository
       extends JpaRepository<TaskComment, Integer> {

    List<TaskComment>
    findByTaskIdOrderByCommentedAtAsc(
            int taskId);
}