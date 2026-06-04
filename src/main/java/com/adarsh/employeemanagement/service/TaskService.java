package com.adarsh.employeemanagement.service;

import java.util.List;
import com.adarsh.employeemanagement.dto.CommentResponse;
import com.adarsh.employeemanagement.model.Project;
import com.adarsh.employeemanagement.repository.ProjectRepository;
import com.adarsh.employeemanagement.dto.CommentRequest;
import com.adarsh.employeemanagement.model.TaskComment;
import com.adarsh.employeemanagement.repository.TaskCommentRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.adarsh.employeemanagement.dto.ActivityResponse;
import com.adarsh.employeemanagement.model.TaskActivity;
import com.adarsh.employeemanagement.repository.TaskActivityRepository;
import org.springframework.web.multipart.MultipartFile;

import com.adarsh.employeemanagement.dto.AttachmentResponse;
import com.adarsh.employeemanagement.model.TaskAttachment;
import com.adarsh.employeemanagement.repository.TaskAttachmentRepository;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Sort;
import java.util.stream.Collectors;
import com.adarsh.employeemanagement.dto.OverdueTaskResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.dto.DashboardResponse;
import com.adarsh.employeemanagement.dto.EmployeeDashboardResponse;
import com.adarsh.employeemanagement.dto.TaskRequest;
import com.adarsh.employeemanagement.dto.TaskResponse;
import com.adarsh.employeemanagement.model.Employee;
import com.adarsh.employeemanagement.model.Task;
import com.adarsh.employeemanagement.model.User;
import com.adarsh.employeemanagement.model.enums.TaskStatus;
import com.adarsh.employeemanagement.repository.EmployeeRepository;
import com.adarsh.employeemanagement.repository.TaskRepository;
import com.adarsh.employeemanagement.repository.UserRepository;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    
    private TaskActivityRepository
    taskActivityRepository;
    
    private NotificationService
    notificationService;
    
    private TaskCommentRepository
    taskCommentRepository;

    private EmployeeRepository employeeRepository;

    private UserRepository userRepository;
    
    private TaskAttachmentRepository
    taskAttachmentRepository;
    
    private ProjectRepository
    projectRepository;

    public TaskService(
    		ProjectRepository
    		projectRepository,
    		TaskAttachmentRepository
    		taskAttachmentRepository,
    		TaskActivityRepository
    		taskActivityRepository,
            TaskRepository taskRepository,
            EmployeeRepository employeeRepository,
            UserRepository userRepository,
            TaskCommentRepository taskCommentRepository,
            NotificationService notificationService) {
    	
    	this.projectRepository =
    	        projectRepository;

        this.taskRepository =
                taskRepository;
        
        this.taskActivityRepository =
                taskActivityRepository;
        
        this.taskAttachmentRepository =
                taskAttachmentRepository;

        this.employeeRepository =
                employeeRepository;

        this.userRepository =
                userRepository;

        this.taskCommentRepository =
                taskCommentRepository;
        
        this.notificationService =
                notificationService;
    }

    public List<Task> getTasks() {

        return taskRepository.findAll();
    }
    
    public CommentResponse
    addComment(
    int taskId,
    CommentRequest request) {

 Task task =
         taskRepository
         .findById(taskId)
         .orElseThrow(
             () -> new RuntimeException(
                 "Task not found"));

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

 TaskComment comment =
         new TaskComment();

 comment.setComment(
         request.getComment());

 comment.setTask(task);

 comment.setCommentedBy(user);

 TaskComment savedComment =
	        taskCommentRepository
	        .save(comment);
 
 createActivity(
	        task,
	        user,
	        "Added comment");

	return convertToCommentResponse(
	        savedComment);
}
    
    public List<CommentResponse>
    getTaskComments(
    int taskId) {

 List<TaskComment> comments =
         taskCommentRepository
         .findByTaskIdOrderByCommentedAtAsc(
                 taskId);

 return comments.stream()
         .map(this::convertToCommentResponse)
         .toList();
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
           TaskStatus status) {

        List<Task> tasks =
                taskRepository
                .findByAssignedEmployeeIdAndStatus(
                        employeeId,
                        status);

        return tasks.stream()
                .map(this::convertToTaskResponse)
                .toList();
    }

    public Task updateMyTaskStatus(
            int taskId,
            TaskStatus status) {

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
        createActivity(
                task,
                user,
                "Changed status to "
                + status);

        return taskRepository.save(task);
    }

    public TaskResponse addTask(
            TaskRequest taskRequest) {

        Employee employee =
                employeeRepository
                .findById(
                    taskRequest.getEmployeeId())
                .orElseThrow(
                    () -> new RuntimeException(
                        "Employee not found"));
        
        Project project =
                projectRepository
                .findById(
                    taskRequest.getProjectId())
                .orElseThrow(
                    () -> new RuntimeException(
                        "Project not found"));

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
        
        if (taskRequest.getDueDate()
                .isBefore(LocalDate.now())) {

            throw new RuntimeException(
                    "Due date cannot be in the past");
        }

        Task task = new Task();

        task.setTitle(
                taskRequest.getTitle());

        task.setDescription(
                taskRequest.getDescription());

        task.setStatus(
                taskRequest.getStatus());

        task.setPriority(
                taskRequest.getPriority());

        task.setDueDate(
                taskRequest.getDueDate());

        task.setAssignedEmployee(
                employee);

        task.setProject(
                project);

        task.setAssignedBy(
                manager);

        Task savedTask =
                taskRepository.save(task);

        createActivity(
                savedTask,
                manager,
                "Created task: "
                + savedTask.getTitle());

        return convertToResponse(
                savedTask);
    }

    private TaskResponse
    convertToResponse(
    Task task) {

TaskResponse response =
        new TaskResponse();

response.setTitle(
        task.getTitle());

response.setStatus(
        task.getStatus());

response.setPriority(
        task.getPriority());

response.setDueDate(
        task.getDueDate());

response.setTaskDuration(
        task.getTaskDuration());

if (task.getProject() != null) {

    response.setProjectName(
            task.getProject()
                .getName());
}

if (task.getAssignedEmployee()
        != null) {

    response.setEmployeeName(
            task.getAssignedEmployee()
                .getName());
}

if (task.getAssignedBy()
        != null) {

    response.setManagerName(
            task.getAssignedBy()
                .getUsername());
}

return response;
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
                        TaskStatus.COMPLETED);

        List<Task> pendingTasks =
                taskRepository
                .findByAssignedEmployeeIdAndStatus(
                        user.getId(),
                        TaskStatus.PENDING);

        List<Task> inProgressTasks =
                taskRepository
                .findByAssignedEmployeeIdAndStatus(
                        user.getId(),
                        TaskStatus.IN_PROGRESS);

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

        existingTask.setDueDate(
                updatedTask.getDueDate());

        existingTask.setStatus(
                updatedTask.getStatus());

        existingTask.setPriority(
                updatedTask.getPriority());

        return taskRepository.save(
                existingTask);
    }
    
    private AttachmentResponse
    convertToAttachmentResponse(
    TaskAttachment attachment) {

AttachmentResponse response =
        new AttachmentResponse();

response.setFileName(
        attachment.getFileName());

response.setFileType(
        attachment.getFileType());

response.setUploadedBy(
        attachment.getUploadedBy()
        .getUsername());

response.setUploadedAt(
        attachment.getUploadedAt());

return response;
}
    
    public AttachmentResponse
    uploadAttachment(
    int taskId,
    MultipartFile file)
    throws IOException {

 Task task =
         taskRepository
         .findById(taskId)
         .orElseThrow(
             () -> new RuntimeException(
                 "Task not found"));

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

 String uploadDir =
         "uploads/";

 Files.createDirectories(
         Paths.get(uploadDir));

 String fileName =
	        System.currentTimeMillis()
	        + "_"
	        + file.getOriginalFilename()
	                .replace(" ", "_");

	String filePath =
	        uploadDir
	        + fileName;

 Path path =
         Paths.get(filePath);

 Files.copy(
         file.getInputStream(),
         path,
         StandardCopyOption.REPLACE_EXISTING);

 TaskAttachment attachment =
         new TaskAttachment();

 attachment.setFileName(
	        fileName);

 attachment.setFileType(
         file.getContentType());

 attachment.setFilePath(
         filePath);

 attachment.setTask(task);

 attachment.setUploadedBy(user);

 TaskAttachment savedAttachment =
         taskAttachmentRepository
         .save(attachment);
 
 createActivity(
	        task,
	        user,
	        "Uploaded file: "
	        + fileName);

 return convertToAttachmentResponse(
         savedAttachment);
}

    public List<TaskResponse>
           getMyTasksByStatus(
           TaskStatus status) {

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
                .map(this::convertToTaskResponse)
                .collect(Collectors.toList());
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
                        TaskStatus.COMPLETED);

        List<Task> pendingTasks =
                taskRepository
                .findByAssignedEmployeeIdAndStatus(
                        employeeId,
                        TaskStatus.PENDING);

        List<Task> inProgressTasks =
                taskRepository
                .findByAssignedEmployeeIdAndStatus(
                        employeeId,
                        TaskStatus.IN_PROGRESS);

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

        response.setPriority(
                task.getPriority());

        response.setDueDate(
                task.getDueDate());

        return response;
    }
    
    public List<OverdueTaskResponse>
    getOverdueTasks() {

 List<Task> overdueTasks =
         taskRepository
         .findByDueDateBeforeAndStatusNot(
                 LocalDate.now(),
                 TaskStatus.COMPLETED);

 return overdueTasks.stream()
         .map(task -> {

             OverdueTaskResponse response =
                     new OverdueTaskResponse();

             response.setEmployeeName(
                     task.getAssignedEmployee()
                     .getName());

             response.setTaskTitle(
                     task.getTitle());

             response.setPriority(
                     task.getPriority());

             response.setStatus(
                     task.getStatus());

             response.setDueDate(
                     task.getDueDate());

             long overdueDays =
                     java.time.temporal.ChronoUnit.DAYS
                     .between(
                         task.getDueDate(),
                         LocalDate.now());

             response.setOverdueDays(
                     overdueDays);

             return response;

         }).toList();
}
    
    public Page<TaskResponse>
    searchTasks(
    String keyword,
    int page,
    int size,
    String sortBy) {

 Pageable pageable =
         PageRequest.of(
                 page,
                 size,
                 Sort.by(sortBy));

 Page<Task> tasks =
         taskRepository
         .findByTitleContainingIgnoreCase(
                 keyword,
                 pageable);

 return tasks.map(
         this::convertToTaskResponse);
}
    
    public Page<TaskResponse>
    searchMyTasks(
    String keyword,
    int page,
    int size,
    String sortBy) {

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

 Pageable pageable =
         PageRequest.of(
                 page,
                 size,
                 Sort.by(sortBy));

 Page<Task> tasks =
	        taskRepository
	        .findByAssignedEmployeeIdAndTitleContainingIgnoreCase(
	                user.getId(),
	                keyword,
	                pageable);

	return tasks.map(
	        this::convertToTaskResponse);
}

    private CommentResponse
    convertToCommentResponse(
    TaskComment comment) {

CommentResponse response =
        new CommentResponse();

response.setComment(
        comment.getComment());

response.setCommentedBy(
        comment.getCommentedBy()
        .getUsername());

response.setCommentedAt(
        comment.getCommentedAt());

return response;
}
    
    public List<AttachmentResponse>
    getTaskAttachments(
    int taskId) {

 List<TaskAttachment> attachments =
         taskAttachmentRepository
         .findByTaskId(taskId);

 return attachments.stream()
         .map(this::convertToAttachmentResponse)
         .toList();
}
    
    private void
    createActivity(
    Task task,
    User user,
    String action) {

 TaskActivity activity =
         new TaskActivity();

 activity.setTask(task);

 activity.setPerformedBy(user);

 activity.setAction(action);

 taskActivityRepository
         .save(activity);
}
    
    public List<ActivityResponse>
    getTaskActivities(
    int taskId) {

 List<TaskActivity> activities =
         taskActivityRepository
         .findByTaskIdOrderByActivityTimeDesc(
                 taskId);

 return activities.stream()
         .map(activity -> {

             ActivityResponse response =
                     new ActivityResponse();

             response.setAction(
                     activity.getAction());

             response.setPerformedBy(
                     activity.getPerformedBy()
                     .getUsername());

             response.setActivityTime(
                     activity.getActivityTime());

             return response;

         }).toList();
}
    
    public String deleteTask(
            int id) {

        taskRepository.deleteById(id);

        return "Task deleted successfully";
    }
}