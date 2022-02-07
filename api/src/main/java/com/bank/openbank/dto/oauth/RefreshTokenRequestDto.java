package com.bank.openbank.dto.oauth;

import lombok.Data;

@Data
public class RefreshTokenRequestDto {
	private String client_id;
	private String client_secret;
	private String refresh_token;
	private String scope;
	private String grant_type;

	public void setRefreshTokenRequestDto(String client_id, String client_secret, String scope, String grant_type) {
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.scope = scope;
		this.grant_type = grant_type;
	}
}
