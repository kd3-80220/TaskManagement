package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custome_exception.ResourceNotFoundException;
import com.app.dao.TaskDao;
import com.app.dto.TaskDto;
import com.app.entities.Task;

@Service
@Transactional
public class TaskImpl implements TaskInterface{
	
	@Autowired
	private TaskDao taskDao;

	@Override
	public String createTask(TaskDto taskDto) {
		Task newTask=new Task();
		newTask.setCreatedDate(LocalDate.now());
		newTask.setDdate(taskDto.getDueDate());
		newTask.setDescription(taskDto.getDescription());
		newTask.setPriority(taskDto.getPriority());
		newTask.setStatus(taskDto.getStatus());
		newTask.setTitle(taskDto.getTitle());
		newTask.setUpdatedDate(LocalDate.now());
		newTask.setUserId(1);
		
		Task t=taskDao.save(newTask);
		if(t!=null)
		return "task created";
		else
			return "task not created";	
	}

	@Override
	public List<Task> getAllTasks() {
		List<Task>allTasks=taskDao.findAll();
		
		return allTasks;
	}

	@Override
	public String updateTask(Long id,Task t) {
		Task t1=taskDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task", "Id", id));
		t1.setTitle(t.getTitle());
		t1.setDescription(t.getDescription());
		t1.setStatus(t.getStatus());
		t1.setPriority(t.getPriority());
		t1.setUpdatedDate(LocalDate.now());
		Task test=taskDao.save(t1);
		if(test!=null)
		return "Task updated";
		else
		return "Task not updated";
	}

	@Override
	public String deleteTask(Long id) {
		Task t=taskDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task", "Id", id));
		taskDao.delete(t);
		return "Task deleted";
	}

}
