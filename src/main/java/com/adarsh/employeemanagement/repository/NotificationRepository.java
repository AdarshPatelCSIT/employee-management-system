package com.adarsh.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.employeemanagement.model.Notification;

public interface NotificationRepository
       extends JpaRepository<
       Notification,
       Integer> {

    List<Notification>
    findByUsernameOrderByCreatedAtDesc(
            String username);
}