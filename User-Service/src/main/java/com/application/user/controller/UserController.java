package com.application.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.user.controller.dto.request.UserRequest;
import com.application.user.controller.handler.UserHandler;

@Validated
@RestController
public class UserController {

	private UserHandler userHandler;

	@Autowired
	private UserController(UserHandler userHandler) {

		this.userHandler = userHandler;
	}

	private final String USER_ID = "userId";

	@PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody UserRequest userRequest) {
		return userHandler.handleRequest(UserHandler.RequestType.CREATE_USER, null, userRequest);
	}

	@PostMapping(value = "/users/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createMany(@RequestBody List<UserRequest> userRequest) {
		return userHandler.handleRequest(UserHandler.RequestType.CREATE_USER_LIST, null, userRequest);
	}

	@GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> get(@RequestParam(name = USER_ID, required = false) @PathVariable(USER_ID) String userId) {
		return userHandler.handleRequest(UserHandler.RequestType.GET_USER, userId, null);
	}

	@PutMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(
			@RequestParam(name = USER_ID, required = false) @PathVariable(USER_ID) String userId,
			@RequestBody UserRequest userRequest) {
		return userHandler.handleRequest(UserHandler.RequestType.UPDATE_USER, userId, userRequest);
	}

	@DeleteMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(
			@RequestParam(name = USER_ID, required = false) @PathVariable(USER_ID) String userId) {
		return userHandler.handleRequest(UserHandler.RequestType.DELETE_USER, userId, null);

	}

}
