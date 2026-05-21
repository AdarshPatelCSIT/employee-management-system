package com.adarsh.employeemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.employeemanagement.model.User;

public interface UserRepository
       extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameOrEmail(
            String username,
            String email);

    Optional<User> findByEmail(
            String email);
}