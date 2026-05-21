package com.adarsh.employeemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.dto.OtpVerificationRequest;
import com.adarsh.employeemanagement.service.EmailService;
import com.adarsh.employeemanagement.service.OtpService;

@RestController
public class OtpController {

    private OtpService otpService;

    private EmailService emailService;

    public OtpController(
            OtpService otpService,
            EmailService emailService) {

        this.otpService = otpService;
        this.emailService = emailService;
    }

    @GetMapping("/send-otp")
    public String sendOtp(
            @RequestParam String email) {

        String otp =
                otpService.generateOtp(email);

        emailService.sendEmail(
                email,
                "Your OTP Code",
                "Your OTP is: " + otp);

        return "OTP sent successfully!";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestBody
            OtpVerificationRequest request) {

        boolean isValid =
                otpService.verifyOtp(
                        request.getEmail(),
                        request.getOtp());

        if (isValid) {

            return "OTP verified successfully!";
        }

        return "Invalid OTP!";
    }
}