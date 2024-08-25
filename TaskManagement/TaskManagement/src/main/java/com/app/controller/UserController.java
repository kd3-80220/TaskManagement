
package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.TaskDto;
import com.app.dto.UserDto;
import com.app.entities.JwtRequest;
import com.app.entities.JwtResponse;
import com.app.entities.Task;
import com.app.entities.User;
import com.app.security.JwtHelper;
import com.app.service.TaskInterface;
import com.app.service.UserInterface;
import com.app.service.UserService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
    private TaskInterface taskInterface;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private UserInterface register;
	
	@GetMapping("/taskmanagement/user/getUser")
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(register.getAllUsers());
	}
	
	@GetMapping("/taskmanagement/user/getUser/{id}")
	public ResponseEntity<User> getUserInfo(@PathVariable Long id)
	{
		return ResponseEntity.ok(register.getUserInfo(id));
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
		this.doAuthenticate(request.getEmail(), request.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);
		
		JwtResponse response = JwtResponse.builder()
				.jwtToken(token)
				.status("success")
				.username(userDetails.getUsername()).build();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authManager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new RuntimeException("Invalid Username or password");
		}
	}

    @PostMapping("/taskmanagement/user/createTask")
    public String createTask(@RequestBody TaskDto task) {
        return taskInterface.createTask(task);
    }

    @GetMapping("/taskmanagement/user/allTasks")
    public List<Task> getAllTasks() {
        return taskInterface.getAllTasks();
    }

    @PutMapping("/taskmanagement/user/updateTask/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody Task t) {
        return taskInterface.updateTask(id, t);
    }
    
    @DeleteMapping("/taskmanagement/user/deleteTask/{id}")
    public String updateTask(@PathVariable Long id) {
        return taskInterface.deleteTask(id);
    }
	@PutMapping("/taskmanagement/user/updateUser/{uid}")
	public ResponseEntity<User> updateUser(@PathVariable Long uid, @RequestBody UserDto user) {
		User u = register.updateUser(uid,user);
		return ResponseEntity.ok(u);
	}
	
	@DeleteMapping("/taskmanagement/user/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
		register.delUser(userId);
		return new ResponseEntity(new ApiResponse("User Deleted Succesfully",true),HttpStatus.OK);
	}
	
	@PostMapping("/user/createUser")
	public String addNewUser(@RequestBody UserDto  userDto) {
		return register.addNewUser(userDto);
		
	}
	
	@PutMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody String email)
	{
		return new ResponseEntity<>(register.forgotPassword(email),HttpStatus.OK);
	}
	
	@GetMapping("/taskmanagement/user/getUserByEmail/{email}")
	public User getByEmail(@PathVariable String email){
		User u = register.getByMail(email);
		return u;
	}


}
