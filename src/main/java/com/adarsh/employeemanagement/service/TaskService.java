package com.adarsh.employeemanagement.service;
import java.time.LocalDateTime;
import com.adarsh.employeemanagement.dto.DashboardResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import com.adarsh.employeemanagement.dto.EmployeeDashboardResponse;

import com.adarsh.employeemanagement.dto.TaskResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.dto.TaskRequest;
import com.adarsh.employeemanagement.model.Employee;
import com.adarsh.employeemanagement.model.Task;
import com.adarsh.employeemanagement.model.User;
import com.adarsh.employeemanagement.repository.EmployeeRepository;
import com.adarsh.employeemanagement.repository.TaskRepository;
import com.adarsh.employeemanagement.repository.UserRepository;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    private EmployeeRepository employeeRepository;

    private UserRepository userRepository;

    public TaskService(
            TaskRepository taskRepository,
            EmployeeRepository employeeRepository,
            UserRepository userRepository) {

        this.taskRepository =
                taskRepository;

        this.employeeRepository =
                employeeRepository;

        this.userRepository =
                userRepository;
    }

    public List<Task> getTasks() {

        return taskRepository.findAll();
    }
    
    public List<Task> getMyTasks() {

        Authentication authentication =
                SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository
                .findByUsername(username)
                .orElseThrow(
                    () -> new RuntimeException(
                        "User not found"));

        return taskRepository
                .findByAssignedEmployeeId(
                        user.getId());
    }

    public List<Task> getMyTaskHistory(
            int days) {

        Authentication authentication =
                SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository
                .findByUsername(username)
                .orElseThrow(
                    () -> new RuntimeException(
                        "User not found"));

        LocalDateTime dateTime =
                LocalDateTime.now()
                .minusDays(days);

        return taskRepository
                .findByAssignedEmployeeIdAndCompletedAtAfter(
                        user.getId(),
                        dateTime);
    }
    
    public List<Task> getMyTaskHistoryByRange(
            LocalDate startDate,
            LocalDate endDate) {

        Authentication authentication =
                SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository
                .findByUsername(username)
                .orElseThrow(
                    () -> new RuntimeException(
                        "User not found"));

        LocalDateTime startDateTime =
                startDate.atStartOfDay();

        LocalDateTime endDateTime =
                endDate.atTime(23, 59, 59);

        return taskRepository
                .findByAssignedEmployeeIdAndCompletedAtBetween(
                        user.getId(),
                        startDateTime,
                        endDateTime);
    }
    
    public List<TaskResponse>
    getEmployeeTasksByStatus(
    int employeeId,
    String status) {

 List<Task> tasks =
         taskRepository
         .findByAssignedEmployeeIdAndStatus(
                 employeeId,
                 status);

 return tasks.stream()
         .map(task -> {

             TaskResponse response =
                     new TaskResponse();

             response.setTitle(
                     task.getTitle());

             response.setStatus(
                     task.getStatus());

             response.setTaskDuration(
                     task.getTaskDuration());

             return response;

         }).toList();
}
    
    public Task updateMyTaskStatus(
            int taskId,
            String status) {

        Authentication authentication =
                SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository
                .findByUsername(username)
                .orElseThrow(
                    () -> new RuntimeException(
                        "User not found"));

        Task task =
                taskRepository
                .findById(taskId)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Task not found"));

        if (task.getAssignedEmployee() == null
                || task.getAssignedEmployee().getId()
                != user.getId()) {

            throw new RuntimeException(
                    "You cannot update this task");
        }

        task.setStatus(status);

        return taskRepository.save(task);
    }
    
    public Task addTask(
            TaskRequest taskRequest) {

        Employee employee =
                employeeRepository
                .findById(
                    taskRequest.getEmployeeId())
                .orElseThrow(
                    () -> new RuntimeException(
                        "Employee not found"));

        Authentication authentication =
                SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username =
                authentication.getName();

        User manager =
                userRepository
                .findByUsername(username)
                .orElseThrow(
                    () -> new RuntimeException(
                        "User not found"));

        Task task = new Task();

        task.setTitle(
                taskRequest.getTitle());

        task.setDescription(
                taskRequest.getDescription());

        task.setStatus(
                taskRequest.getStatus());

        task.setAssignedEmployee(
                employee);

        task.setAssignedBy(
                manager);

        return taskRepository.save(task);
    }

    public DashboardResponse
    getMyDashboard() {

 Authentication authentication =
         SecurityContextHolder
         .getContext()
         .getAuthentication();

 String username =
         authentication.getName();

 User user =
         userRepository
         .findByUsername(username)
         .orElseThrow(
             () -> new RuntimeException(
                 "User not found"));

 List<Task> allTasks =
         taskRepository
         .findByAssignedEmployeeId(
                 user.getId());

 List<Task> completedTasks =
         taskRepository
         .findByAssignedEmployeeIdAndStatus(
                 user.getId(),
                 "COMPLETED");

 List<Task> pendingTasks =
         taskRepository
         .findByAssignedEmployeeIdAndStatus(
                 user.getId(),
                 "PENDING");

 List<Task> inProgressTasks =
         taskRepository
         .findByAssignedEmployeeIdAndStatus(
                 user.getId(),
                 "IN_PROGRESS");

 DashboardResponse response =
         new DashboardResponse();

 response.setTotalTasks(
         allTasks.size());

 response.setCompletedTasks(
         completedTasks.size());

 response.setPendingTasks(
         pendingTasks.size());

 response.setInProgressTasks(
         inProgressTasks.size());

 return response;
}
    
    public Task updateTask(
            int id,
            Task updatedTask) {

        Task existingTask =
                taskRepository
                .findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Task not found"));

        existingTask.setTitle(
                updatedTask.getTitle());

        existingTask.setDescription(
                updatedTask.getDescription());

        existingTask.setStatus(
                updatedTask.getStatus());

        return taskRepository.save(
                existingTask);
    }
    
    public List<TaskResponse>
    getMyTasksByStatus(
    String status) {

 Authentication authentication =
         SecurityContextHolder
         .getContext()
         .getAuthentication();

 String username =
         authentication.getName();

 User user =
         userRepository
         .findByUsername(username)
         .orElseThrow(
             () -> new RuntimeException(
                 "User not found"));

 List<Task> tasks =
         taskRepository
         .findByAssignedEmployeeIdAndStatus(
                 user.getId(),
                 status);

 return tasks.stream()
         .map(task -> {

             TaskResponse response =
                     new TaskResponse();

             response.setTitle(
                     task.getTitle());

             response.setStatus(
                     task.getStatus());

             response.setTaskDuration(
                     task.getTaskDuration());

             return response;

         }).collect(Collectors.toList());
}
    
   
    public EmployeeDashboardResponse
    getEmployeeDashboard(
    int employeeId) {

 Employee employee =
         employeeRepository
         .findById(employeeId)
         .orElseThrow(
             () -> new RuntimeException(
                 "Employee not found"));

 List<Task> allTasks =
         taskRepository
         .findByAssignedEmployeeId(
                 employeeId);

 List<Task> completedTasks =
         taskRepository
         .findByAssignedEmployeeIdAndStatus(
                 employeeId,
                 "COMPLETED");

 List<Task> pendingTasks =
         taskRepository
         .findByAssignedEmployeeIdAndStatus(
                 employeeId,
                 "PENDING");

 List<Task> inProgressTasks =
         taskRepository
         .findByAssignedEmployeeIdAndStatus(
                 employeeId,
                 "IN_PROGRESS");

 EmployeeDashboardResponse response =
         new EmployeeDashboardResponse();

 response.setEmployeeName(
         employee.getName());

 response.setTotalTasks(
         allTasks.size());

 response.setCompletedTasks(
         completedTasks.size());

 response.setPendingTasks(
         pendingTasks.size());

 response.setInProgressTasks(
         inProgressTasks.size());

 response.setCompletedTaskList(
         completedTasks.stream()
         .map(this::convertToTaskResponse)
         .collect(Collectors.toList()));

 response.setPendingTaskList(
         pendingTasks.stream()
         .map(this::convertToTaskResponse)
         .collect(Collectors.toList()));

 response.setInProgressTaskList(
         inProgressTasks.stream()
         .map(this::convertToTaskResponse)
         .collect(Collectors.toList()));

 return response;
}
    
    private TaskResponse
    convertToTaskResponse(
    Task task) {

TaskResponse response =
        new TaskResponse();

response.setTitle(
        task.getTitle());

response.setStatus(
        task.getStatus());

response.setTaskDuration(
        task.getTaskDuration());

return response;
}

    public String deleteTask(
            int id) {

        taskRepository.deleteById(id);

        return "Task deleted successfully";
    }
}