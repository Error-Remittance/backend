package com.bank.openbank.dto.oauth;

import lombok.Data;

@Data
public class RefreshTokenResponseDto {
	private String access_token;
	private String token_type; // "Bearer" 고정
	private int expires_in;
	private String scope;
	private String user_equ_no;

}
