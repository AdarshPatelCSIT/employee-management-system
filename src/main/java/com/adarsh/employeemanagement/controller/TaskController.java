package com.adarsh.employeemanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.employeemanagement.model.Task;
import com.adarsh.employeemanagement.service.TaskService;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {

        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {

        return taskService.getTasks();
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {

        return taskService.addTask(task);
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable int id,
                           @RequestBody Task updatedTask) {

        return taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable int id) {

        return taskService.deleteTask(id);
    }
}