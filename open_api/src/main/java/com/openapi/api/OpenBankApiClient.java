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
import com.openapi.dto.RefreshTokenRequestDto;
import com.openapi.dto.RefreshTokenResponseDto;

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
		HttpHeaders httpHeaders = new HttpHeaders();
		this.httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		bankRequestToken.setBankRequestToken(clientId, clientSecret, redirect_uri, "authorization_code");

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("code", bankRequestToken.getCode());
		parameters.add("client_id", bankRequestToken.getClient_id());
		parameters.add("client_secret", bankRequestToken.getClient_secret());
		parameters.add("redirect_uri", bankRequestToken.getRedirect_uri());
		parameters.add("grant_type", bankRequestToken.getGrant_type());

		HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<>(parameters, this.httpHeaders);

		return restTemplate.exchange(base_url + "/oauth/2.0/token", HttpMethod.POST,
			param, BankResponseToken.class).getBody();
	}

	/**
	 * 토큰 갱신
	 * 아직 시도는 안해봄₩
	 */
	public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
		// POST 방식, 호출 uri : /oauth/2.0/token
		// Header 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content_type", "application/x-www-form-urlencoded;charset=UTF-8");
		// Body 설정

		refreshTokenRequestDto.setRefreshTokenRequestDto(clientId, clientSecret, "login inquiry transfer",
			"refresh_token");
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("client_id", refreshTokenRequestDto.getClient_id());
		parameters.add("client_secret", refreshTokenRequestDto.getClient_secret());
		parameters.add("refresh_token", refreshTokenRequestDto.getRefresh_token());
		parameters.add("scope", refreshTokenRequestDto.getScope());
		parameters.add("grant_type", refreshTokenRequestDto.getGrant_type());

		HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<>(parameters, httpHeaders);

		return restTemplate.exchange(base_url + "/oauth/2.0/token",HttpMethod.POST,param,RefreshTokenResponseDto.class).getBody();

	}

	/**
	 * 헤더에 엑세스 토큰넣기
	 * @param access_token
	 * @return
	 */
	public HttpHeaders setHeader(String access_token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		this.httpHeaders.add("Authorization", "Bearer " + access_token);
		return this.httpHeaders;
	}
}
