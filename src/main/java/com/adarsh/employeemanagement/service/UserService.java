package com.adarsh.employeemanagement.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.dto.AuthResponse;
import com.adarsh.employeemanagement.dto.LoginRequest;
import com.adarsh.employeemanagement.dto.ResetPasswordRequest;
import com.adarsh.employeemanagement.exception.ResourceNotFoundException;
import com.adarsh.employeemanagement.model.User;
import com.adarsh.employeemanagement.repository.UserRepository;
import com.adarsh.employeemanagement.security.JwtService;

@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private JwtService jwtService;

    private OtpService otpService;

    public UserService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            JwtService jwtService,
            OtpService otpService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.otpService = otpService;
    }

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public User registerUser(User user) {

        boolean isVerified =
                otpService.isEmailVerified(
                        user.getEmail());

        if (!isVerified) {

            throw new RuntimeException(
                    "Email not verified with OTP");
        }

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()));

        return userRepository.save(user);
    }

    public AuthResponse loginUser(
            LoginRequest loginRequest) {

        String loginInput =
                loginRequest.getUsername().trim();

        User user = userRepository
                .findByUsernameOrEmail(
                        loginInput,
                        loginInput)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        boolean passwordMatches =
                passwordEncoder.matches(
                        loginRequest.getPassword(),
                        user.getPassword());

        if (!passwordMatches) {

            throw new ResourceNotFoundException(
                    "Invalid password");
        }

        String accessToken =
                jwtService.generateToken(
                        user.getUsername(),
                        user.getRole());

        String refreshToken =
                jwtService.generateRefreshToken(
                        user.getUsername());

        return new AuthResponse(
                accessToken,
                refreshToken);
    }

    public String resetPassword(
            ResetPasswordRequest request) {

        boolean isVerified =
                otpService.isEmailVerified(
                        request.getEmail());

        if (!isVerified) {

            return "OTP verification required!";
        }

        User user = userRepository
                .findByEmail(
                        request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()));

        userRepository.save(user);

        return "Password reset successful!";
    }
}