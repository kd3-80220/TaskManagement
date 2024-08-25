package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="t_id")
	private Long tid;
	
	@Column(length = 30, nullable = false)
	private String title;
	@Column(length = 50, nullable = false)
	private String description;
	@Column(length = 20,nullable = false)
	private String status;
	@Column(length = 20, nullable = false)
	private String priority;
	@Column(name = "due_date")
	private LocalDate ddate;
	@Column(name = "created_date")
	private LocalDate createdDate;
	@Column(name = "updated_date")
	private LocalDate updatedDate;
	@Column(name = "user_id")
	private int userId;
	
	public Task(String title,String description, String status, String priority)
	{
	    this.title=	title;
	    this.description= description;
	    this.priority= priority;
	    this.status=status;
	}
}
