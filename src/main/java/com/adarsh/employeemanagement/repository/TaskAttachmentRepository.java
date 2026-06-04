package com.adarsh.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.employeemanagement.model.TaskAttachment;

public interface TaskAttachmentRepository
       extends JpaRepository<TaskAttachment, Integer> {

    List<TaskAttachment>
    findByTaskId(
            int taskId);
}