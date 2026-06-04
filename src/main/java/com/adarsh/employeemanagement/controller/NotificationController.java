package com.adarsh.employeemanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.dto.NotificationResponse;
import com.adarsh.employeemanagement.service.NotificationService;

@RestController
public class NotificationController {

    private NotificationService
            notificationService;

    public NotificationController(
            NotificationService notificationService) {

        this.notificationService =
                notificationService;
    }

    @GetMapping("/notifications")
    public ResponseEntity<
            List<NotificationResponse>>
            getNotifications(
            Authentication authentication) {

        return ResponseEntity.ok(
                notificationService
                .getNotifications(
                        authentication.getName()));
    }
    
    

    @PutMapping(
            "/notifications/{id}/read")
    public ResponseEntity<String>
            markAsRead(
            @PathVariable int id) {

        return ResponseEntity.ok(
                notificationService
                .markAsRead(id));
    }
}