package com.adarsh.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.employeemanagement.model.Project;

public interface ProjectRepository
       extends JpaRepository<Project, Integer> {

}