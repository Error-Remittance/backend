package com.bank.openbank.dto.oauth;

import lombok.Data;

@Data
public class IssueTokenRequestDto {
	private String client_id;
	private String client_secret;
	private String scope;
	private String grant_type; // 고정값: authorization_code

	public void setBankRequestToken(String client_id, String client_secret, String scope, String grant_type) {
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.scope = scope;
		this.grant_type = grant_type;
	}

}
