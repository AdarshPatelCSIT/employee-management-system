package com.adarsh.employeemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.dto.NotificationResponse;
import com.adarsh.employeemanagement.exception.ResourceNotFoundException;
import com.adarsh.employeemanagement.model.Notification;
import com.adarsh.employeemanagement.repository.NotificationRepository;

@Service
public class NotificationService {

    private NotificationRepository
            notificationRepository;

    public NotificationService(
            NotificationRepository notificationRepository) {

        this.notificationRepository =
                notificationRepository;
    }

    public void createNotification(
            String username,
            String message) {

        Notification notification =
                new Notification();

        notification.setUsername(
                username);

        notification.setMessage(
                message);

        notificationRepository.save(
                notification);
    }

    public List<NotificationResponse>
           getNotifications(
           String username) {

        return notificationRepository
                .findByUsernameOrderByCreatedAtDesc(
                        username)
                .stream()
                .map(notification -> {

                    NotificationResponse
                            response =
                            new NotificationResponse();

                    response.setId(
                            notification.getId());

                    response.setMessage(
                            notification.getMessage());

                    response.setRead(
                            notification.isRead());

                    response.setCreatedAt(
                            notification.getCreatedAt());

                    return response;

                }).toList();
    }

    public String markAsRead(
            int notificationId) {

        Notification notification =
                notificationRepository
                .findById(notificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Notification not found"));

        notification.setRead(true);

        notificationRepository.save(
                notification);

        return "Notification marked as read";
    }
}