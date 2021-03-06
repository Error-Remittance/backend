package com.bank.openbank.dto.oauth;

import lombok.Data;

@Data
public class IssueTokenResponseDto {
	private String access_token;
	private String token_type; // 고정값 : Bearer
	private int expires_in; //Access Token 만료 기간(초)
	private String scope; //Access Token 권한 범위
	private String client_use_code; //사용자일련번호
}
