package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.TaskDto;
import com.app.entities.Task;
import com.app.service.TaskInterface;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {

    @Autowired
    private TaskInterface taskInterface;

    @PostMapping("/create")
    public String createTask(@RequestBody TaskDto task) {
        return taskInterface.createTask(task);
    }

    @GetMapping("/alltasks")
    public List<Task> getAllTasks() {
        return taskInterface.getAllTasks();
    }

    @PutMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody Task t) {
        return taskInterface.updateTask(id, t);
    }
    
    @DeleteMapping("/delete/{id}")
    public String updateTask(@PathVariable Long id) {
        return taskInterface.deleteTask(id);
    }
}

