package com.openapi.dto;

import lombok.Data;

@Data
public class AuthrozationResponseDto {
	private String code;
	private String scope;
	private String client_info;
	private String state;

}
