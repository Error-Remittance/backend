package com.openapi.dto;

import lombok.Data;

@Data
public class BankResponseToken {
	private String access_token;
	private String refresh_token;
	private String token_type; // 고정값 : Bearer
	private int expires_in; //Access Token 만료 기간(초)
	private String scope; //Access Token 권한 범위
	private String user_seq_no; //사용자일련번호
}
