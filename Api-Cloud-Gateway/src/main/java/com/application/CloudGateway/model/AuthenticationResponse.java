package com.application.CloudGateway.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

	private String userId;
	private String accessToken;
	private String refreshToken;
	private Long expiresAt;
	private Collection<String> authorityList;
}
