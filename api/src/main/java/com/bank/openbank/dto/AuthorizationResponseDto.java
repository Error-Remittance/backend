package com.bank.openbank.dto;

import lombok.Data;

@Data
public class AuthorizationResponseDto {
	private String code;
	private String scope;
	private String client_info;
	private String state;
}
