package com.openapi.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.openapi.dto.AuthorizationRequestDto;
import com.openapi.dto.AuthrozationResponseDto;
import com.openapi.dto.BankRequestToken;
import com.openapi.dto.BankResponseToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenBankApiClient {

	private final OpenBankUtil openBankUtil;
	private final HttpHeaders httpHeaders;
	private final RestTemplate restTemplate;

	@Value("${openbank.useCode}")
	private String useCode;
	@Value("${openbank.client-id}")
	private String clientId;
	@Value("${openbank.client-secret}")
	private String clientSecret;

	private final String redirect_uri = "http://localhost:8080/auth/openbank/callback";
	private final String base_url = "https://testapi.openbanking.or.kr";

	/**
	 * 사용자 인증 받기
	 */
	public AuthrozationResponseDto authrizationClient() {
		// HTTP Method GET
		AuthorizationRequestDto requestDto = AuthorizationRequestDto.builder()
			.response_type("code")
			// .client_id(clientId)
			.redirect_uri(redirect_uri)
			.scope("login inquiry tranfer")
			.state("12345678901234567890123456789012")
			.auth_type("0")
			.build();

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("response_type", requestDto.getResponse_type());
		parameters.add("client_id", requestDto.getClient_id());
		parameters.add("redirect_uri", requestDto.getRedirect_uri());
		parameters.add("scope", requestDto.getScope());
		parameters.add("state", requestDto.getResponse_type());
		parameters.add("auth_type", requestDto.getAuth_type());

		HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<>(parameters, null);
		return restTemplate.exchange(base_url + "/oauth/2.0/authorize", HttpMethod.GET, param,
			AuthrozationResponseDto.class).getBody();

	}

	/**
	 * 토큰 발급 요청
	 */
	public BankResponseToken requestToken(BankRequestToken bankRequestToken) {
		log.info("code : " + bankRequestToken.getCode());
		// POST 방식
		// HTTP header
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		bankRequestToken.setBankRequestToken(clientId, clientSecret, redirect_uri, "authorization_code");

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("code", bankRequestToken.getCode());
		parameters.add("client_id", bankRequestToken.getClient_id());
		parameters.add("client_secret", bankRequestToken.getClient_secret());
		parameters.add("redirect_uri", bankRequestToken.getRedirect_uri());
		parameters.add("grant_type", bankRequestToken.getGrant_type());

		HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<>(parameters, httpHeaders);

		return restTemplate.exchange(base_url + "/oauth/2.0/token", HttpMethod.POST,
			param, BankResponseToken.class).getBody();
	}
}
