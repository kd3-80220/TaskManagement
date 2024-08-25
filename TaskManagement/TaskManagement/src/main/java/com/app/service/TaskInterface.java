package com.app.service;

import java.util.List;

import com.app.dto.TaskDto;
import com.app.entities.Task;

public interface TaskInterface {

	String createTask(TaskDto task);

	List<Task> getAllTasks();

	String updateTask(Long id,Task t);

	String deleteTask(Long id);

}
