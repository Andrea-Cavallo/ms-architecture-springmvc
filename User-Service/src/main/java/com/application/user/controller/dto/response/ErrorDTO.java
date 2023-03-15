package com.application.user.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class ErrorDTO {
	@JsonProperty("field")
	private String field;

	@JsonProperty("error")
	private String error;

	@JsonProperty("object")
	private Object object;
}
