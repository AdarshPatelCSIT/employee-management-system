package com.adarsh.employeemanagement.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.dto.AuthResponse;
import com.adarsh.employeemanagement.dto.LoginRequest;
import com.adarsh.employeemanagement.dto.RefreshTokenRequest;
import com.adarsh.employeemanagement.dto.ResetPasswordRequest;
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

        this.userRepository =
                userRepository;

        this.passwordEncoder =
                passwordEncoder;

        this.jwtService =
                jwtService;

        this.otpService =
                otpService;
    }

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public String registerUser(
            User user) {

        boolean usernameExists =
                userRepository
                .findByUsername(
                        user.getUsername())
                .isPresent();

        if (usernameExists) {

            throw new RuntimeException(
                    "Username already exists");
        }

        boolean emailExists =
                userRepository
                .findByEmail(
                        user.getEmail())
                .isPresent();

        if (emailExists) {

            throw new RuntimeException(
                    "Email already exists");
        }

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()));

        userRepository.save(user);

        return "User registered successfully";
    }

    public AuthResponse loginUser(
            LoginRequest request) {

        User user =
                userRepository
                .findByUsernameOrEmail(
                        request.getUsername(),
                        request.getUsername())
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid password");
        }

        String accessToken =
                jwtService.generateToken(user);

        String refreshToken =
                jwtService.generateRefreshToken(
                        user.getUsername());

        return new AuthResponse(
                accessToken,
                refreshToken);
    }

    public AuthResponse refreshToken(
            RefreshTokenRequest request) {

        String username =
                jwtService.extractUsername(
                        request.getRefreshToken());

        User user =
                userRepository
                .findByUsernameOrEmail(
                        username,
                        username)
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"));

        String accessToken =
                jwtService.generateToken(user);

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

        User user =
                userRepository
                .findByEmail(
                        request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"));

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()));

        userRepository.save(user);

        return "Password reset successful";
    }
}