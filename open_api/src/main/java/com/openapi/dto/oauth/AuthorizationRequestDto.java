package com.openapi.dto.oauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizationRequestDto {
	private String response_type;
	private String client_id;
	private String redirect_uri;
	private String scope;
	private String state;
	private String auth_type;

}
