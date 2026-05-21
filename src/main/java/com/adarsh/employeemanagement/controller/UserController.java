package com.adarsh.employeemanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.dto.AuthResponse;
import com.adarsh.employeemanagement.dto.LoginRequest;
import com.adarsh.employeemanagement.dto.RefreshTokenRequest;
import com.adarsh.employeemanagement.dto.ResetPasswordRequest;
import com.adarsh.employeemanagement.model.User;
import com.adarsh.employeemanagement.security.JwtService;
import com.adarsh.employeemanagement.service.UserService;

@RestController
public class UserController {

    private UserService userService;

    private JwtService jwtService;

    public UserController(
            UserService userService,
            JwtService jwtService) {

        this.userService =
                userService;

        this.jwtService =
                jwtService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {

        return userService.getUsers();
    }

    @PostMapping("/register")
    public User registerUser(
            @RequestBody User user) {

        return userService.registerUser(user);
    }

    @PostMapping("/auth/login")
    public AuthResponse loginUser(
            @RequestBody LoginRequest request) {

        return userService.loginUser(request);
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestBody
            ResetPasswordRequest request) {

        return userService.resetPassword(
                request);
    }

    @PostMapping("/refresh-token")
    public String refreshToken(
            @RequestBody
            RefreshTokenRequest request) {

        String username =
                jwtService.extractUsername(
                        request.getRefreshToken());

        return jwtService.generateToken(
                username,
                "ADMIN");
    }
}