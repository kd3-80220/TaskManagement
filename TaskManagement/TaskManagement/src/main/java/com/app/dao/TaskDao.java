package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Task;

public interface TaskDao extends JpaRepository<Task, Long>{

}
