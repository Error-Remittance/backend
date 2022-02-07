package com.bank.openbank.dto.oauth;

import lombok.Data;

@Data
public class IssueTokenRequestDto {
	private String code; // 사용자인증 성공 후 획득한 Authorization Code
	private String client_id;
	private String client_secret;
	private String redirect_uri; // Access Token을 전달받을 Callback URL(Authorization Code 획득 시 요청했던 Callback URL)
	private String grant_type; // 고정값: authorization_code

	public void setBankRequestToken(String client_id, String client_secret, String redirect_uri, String grant_type) {
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.redirect_uri = redirect_uri;
		this.grant_type = grant_type;
	}

}
