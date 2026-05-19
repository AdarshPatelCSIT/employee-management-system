package com.adarsh.employeemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adarsh.employeemanagement.model.Task;
import com.adarsh.employeemanagement.repository.TaskRepository;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {

        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {

        return taskRepository.findAll();
    }

    public Task addTask(Task task) {

        return taskRepository.save(task);
    }

    public Task updateTask(int id, Task updatedTask) {

        updatedTask.setId(id);

        return taskRepository.save(updatedTask);
    }

    public String deleteTask(int id) {

        taskRepository.deleteById(id);

        return "Task deleted successfully";
    }
}