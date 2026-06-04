package com.adarsh.employeemanagement.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.dto.AdminDashboardResponse;
import com.adarsh.employeemanagement.model.enums.TaskStatus;
import com.adarsh.employeemanagement.repository.EmployeeRepository;
import com.adarsh.employeemanagement.repository.ProjectRepository;
import com.adarsh.employeemanagement.repository.TaskRepository;
import com.adarsh.employeemanagement.repository.UserRepository;

@Service
public class AdminDashboardService {

    private EmployeeRepository employeeRepository;

    private UserRepository userRepository;

    private ProjectRepository projectRepository;

    private TaskRepository taskRepository;

    public AdminDashboardService(
            EmployeeRepository employeeRepository,
            UserRepository userRepository,
            ProjectRepository projectRepository,
            TaskRepository taskRepository) {

        this.employeeRepository =
                employeeRepository;

        this.userRepository =
                userRepository;

        this.projectRepository =
                projectRepository;

        this.taskRepository =
                taskRepository;
    }

    public AdminDashboardResponse
           getDashboard() {

        AdminDashboardResponse response =
                new AdminDashboardResponse();

        response.setTotalEmployees(
                employeeRepository.count());

        response.setTotalManagers(
                userRepository.countByRole(
                        "MANAGER"));
        
        response.setTotalAdmins(
                userRepository.countByRole(
                        "ADMIN"));

        response.setTotalProjects(
                projectRepository.count());

        response.setTotalTasks(
                taskRepository.count());

        response.setCompletedTasks(
                taskRepository.countByStatus(
                        TaskStatus.COMPLETED));

        response.setPendingTasks(
                taskRepository.countByStatus(
                        TaskStatus.PENDING));

        response.setInProgressTasks(
                taskRepository.countByStatus(
                        TaskStatus.IN_PROGRESS));

        response.setOverdueTasks(
                taskRepository
                .countByDueDateBeforeAndStatusNot(
                        LocalDate.now(),
                        TaskStatus.COMPLETED));

        return response;
    }
}