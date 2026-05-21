package com.adarsh.employeemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.service.EmailService;

@RestController
public class EmailController {

    private EmailService emailService;

    public EmailController(
            EmailService emailService) {

        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public String sendEmail() {

        emailService.sendEmail(
                "adarsh.patel.csit@gmail.com",
                "Spring Boot Email Test",
                "Email sending is working successfully!");

        return "Email sent successfully!";
    }
}