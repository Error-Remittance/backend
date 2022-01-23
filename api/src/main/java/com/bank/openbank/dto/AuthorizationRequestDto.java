package com.bank.openbank.dto;

import lombok.Data;

@Data
public class AuthorizationRequestDto {
	private String client_id;
	private String scope;
	private String redirect_uri;
	private String auth_type;
	private String response_type;
	private String state;
}
