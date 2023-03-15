package com.application.order.controller.dto.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RestResponse<T> implements Serializable {

	private static final long serialVersionUID = 6607754259039613847L;
	private T output;
	private Map<String, String> errorMessages;
	private transient List<ErrorDTO> validationError;

	public RestResponse(Map<String, String> errorMessages, T output) {
		super();
		this.errorMessages = errorMessages;
		this.output = output;
	}

	public RestResponse(Map<String, String> errorMessages, List<ErrorDTO> validationError, T output) {
		this.errorMessages = errorMessages;
		this.validationError = validationError;
		this.output = output;
	}

	public RestResponse() {
		super();
		this.errorMessages = new HashMap<>();
	}

}