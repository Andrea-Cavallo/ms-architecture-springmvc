package com.application.user.controller.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.application.user.controller.dto.request.UserRequest;
import com.application.user.controller.dto.response.RestResponse;
import com.application.user.controller.dto.response.UserDTO;
import com.application.user.controller.exception.InvalidEmailException;
import com.application.user.service.UserService;
import com.application.user.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserHandler {

	private UserService userService;

	@Autowired
	private UserHandler(UserService userService) {
		this.userService = userService;
	}

	public ResponseEntity<?> handleRequest(RequestType requestType, String transactionId, Object requestData) {
		switch (requestType) {
		case CREATE_USER:
			UserRequest userRequest = (UserRequest) requestData;
			return createUser(userRequest);
		case CREATE_USER_LIST:
			List<UserRequest> userRequestList = (List<UserRequest>) requestData;
			return createUserList(userRequestList);
		case GET_USER:
			return getAUser(transactionId);
		case UPDATE_USER:
			userRequest = (UserRequest) requestData;
			return updateAUser(userRequest, transactionId);
		case DELETE_USER:
			return deleteAUser(transactionId);
		default:
			throw new IllegalArgumentException("Unsupported request type");
		}
	}

	private ResponseEntity<RestResponse<UserDTO>> createUser(UserRequest userRequest) {
		try {
			log.info("Dentro l handler di Create User");

			String uuid = UUID.randomUUID().toString();
			RestResponse<UserDTO> restResponse = new RestResponse<>();
			var userDto = userService.createUser(userRequest, uuid);
			restResponse.setOutput(userDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(restResponse);
		} catch (InvalidEmailException ex) {
			return ValidationExceptionHandler.handle(ex);
		}
	}

	private ResponseEntity<RestResponse<List<UserDTO>>> createUserList(List<UserRequest> userRequestList) {
		log.info("Dentro l handler di MultiCreate User");

		long i = userRequestList.size();
		List<String> uuidList = new ArrayList<>();
		for (int x = 0; x < i; x++) {
			String uuid = UUID.randomUUID().toString();
			uuidList.add(uuid);
		}

		RestResponse<List<UserDTO>> restResponse = new RestResponse<>();
		var userDto = userService.createAListOfUser(userRequestList, uuidList);
		restResponse.setOutput(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(restResponse);
	}

	private ResponseEntity<RestResponse<UserDTO>> getAUser(String transactionId) {
		log.info("Dentro l handler di GET User");

		RestResponse<UserDTO> restResponse = new RestResponse<>();
		var userDto = userService.getUserByTransactionId(transactionId);
		restResponse.setOutput(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(restResponse);
	}

	private ResponseEntity<RestResponse<UserDTO>> updateAUser(UserRequest userRequest, String transactionId) {
		try {
			log.info("Dentro l handler di Update User");

			var stripped = transactionId.strip();
			RestResponse<UserDTO> restResponse = new RestResponse<>();
			var userDto = userService.updateUser(userRequest, stripped);
			restResponse.setOutput(userDto);
			return ResponseEntity.status(HttpStatus.OK).body(restResponse);
		} catch (InvalidEmailException ex) {
			return ValidationExceptionHandler.handle(ex);
		}
	}

	private ResponseEntity<RestResponse<String>> deleteAUser(String transactionId) {
		log.info("Dentro l handler di Delete User");

		var stripped = transactionId.strip();
		RestResponse<String> restResponse = new RestResponse<>();
		Boolean isDeleted = userService.deleteUser(stripped);
		String response = "error ";
		if (Boolean.TRUE.equals(isDeleted)) {
			response = createResponse(stripped);
		}

		restResponse.setOutput(response);

		return ResponseEntity.status(HttpStatus.OK).body(restResponse);
	}

	private String createResponse(String transactionId) {
		StringBuilder a = new StringBuilder("User with ").append(transactionId).append(" was deleted");
		return Utils.stringValue(a);
	}

	public enum RequestType {
		CREATE_USER, CREATE_USER_LIST, GET_USER, UPDATE_USER, DELETE_USER
	}

}
