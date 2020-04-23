package com.sid.app.ws.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.app.ws.service.UserService;
import com.sid.app.ws.shared.UserDto;

@RestController
@RequestMapping("/users")
public class Controller {

	@Autowired
	private Environment env;

	@Autowired
	private UserService userService;

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> getUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(new User("sid", "sidisl16@gmail.com", 1));
	}

	@GetMapping(value = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> getUsersByUserId(@PathVariable(value = "userId") String userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUserId(userId));
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDto> CreateUser(@RequestBody UserDto user) {
		UserDto userResponse = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(userResponse);
	}

	@GetMapping(path = "/test", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Response> getFiles() {
		Response response = new Response();

		Map<String, String> map = new HashMap<>();
		map.put("ample", "<file_content>");
		map.put("generic", "<file_content>");

		response.setData(map);
		response.setStatus("SUCCESS");

		List<String> arr = Arrays.asList("tes", "test");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/status/check")
	public String statusCheck() {
		return "user ms working... on port " + env.getProperty("local.server.port");
	}

	@GetMapping("/token/expiry")
	public String getTokenExpiry() {
		return "Token expiry " + env.getProperty("token.expiry.time");
	}

}

class Response {
	private String status;
	private String errors;
	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

class User {

	@NotNull(message = "misssing user")
	private String user;
	@NotNull
	@Email
	private String email;
	@NotNull
	private int id;

	public User() {
		super();
	}

	public User(String user, String email, int id) {
		super();
		this.user = user;
		this.email = email;
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
