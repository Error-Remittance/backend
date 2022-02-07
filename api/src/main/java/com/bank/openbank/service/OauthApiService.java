package com.bank.openbank.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.bank.openbank.dto.oauth.AuthorizationRequestDto;
import com.bank.openbank.dto.oauth.AuthorizationResponseDto;
import com.bank.openbank.dto.oauth.IssueTokenRequestDto;
import com.bank.openbank.dto.oauth.IssueTokenResponseDto;
import com.bank.openbank.dto.oauth.RefreshTokenRequestDto;
import com.bank.openbank.dto.oauth.RefreshTokenResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OauthApiService {

	private final OpenBankUtil openBankUtil;
	private final RestTemplate restTemplate;

	@Value("${openbank.useCode}")
	private String useCode;
	@Value("${openbank.client-id}")
	private String clientId;
	@Value("${openbank.client-secret}")
	private String clientSecret;
	@Value("${openbank.access-token}")
	private String accessToken;

	private final String redirect_uri = "http://localhost:8080/auth/openbank/callback";
	private final String base_url = "https://testapi.openbanking.or.kr";

	/**
	 * 사용자 인증 받기// 최초
	 * 프론트엔드에서 직접 호출하
	 */
	public AuthorizationResponseDto authrizationClient() {
		// HTTP Method GET
		AuthorizationRequestDto authorizationRequestDto = new AuthorizationRequestDto("code", clientId,
			redirect_uri + "/init", "login inquiry tranfer"
			, "12345678901234567890123456789012", "0");

		String url = base_url + "/user/me";
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
			.queryParam("response_type", authorizationRequestDto.getResponse_type())
			.queryParam("client_id", authorizationRequestDto.getClient_id())
			.queryParam("redirect_uri", authorizationRequestDto.getRedirect_uri())
			.queryParam("scope", authorizationRequestDto.getScope())
			.queryParam("state", authorizationRequestDto.getState())
			.queryParam("auth_type", authorizationRequestDto.getAuth_type())
			.build();

		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
			null, AuthorizationResponseDto.class).getBody();

	}

	/**
	 * 토큰 발급 요청
	 * request 포함 내용 : code(사용자일련번호)
	 */
	public IssueTokenResponseDto requestToken(IssueTokenRequestDto issueTokenRequestDto) {
		log.info("request token start");
		log.info("code : {}", issueTokenRequestDto.getCode());
		// POST 방식
		// HTTP header
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		issueTokenRequestDto.setBankRequestToken(clientId, clientSecret, redirect_uri, "authorization_code");

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("code", issueTokenRequestDto.getCode());
		parameters.add("client_id", issueTokenRequestDto.getClient_id());
		parameters.add("client_secret", issueTokenRequestDto.getClient_secret());
		parameters.add("redirect_uri", issueTokenRequestDto.getRedirect_uri());
		parameters.add("grant_type", issueTokenRequestDto.getGrant_type());

		HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<>(parameters, httpHeaders);

		return restTemplate.exchange(base_url + "/oauth/2.0/token", HttpMethod.POST,
			param, IssueTokenResponseDto.class).getBody();
	}

	/**
	 * 토큰 갱신
	 * POST 방식, 호출 uri : /oauth/2.0/token
	 * request에 포함되어야 하는 값 : code(사용자 인증 성공 후 획득한 Authorization code)
	 */
	public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
		log.info("refresh token start");
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

		return restTemplate.exchange(base_url + "/oauth/2.0/token", HttpMethod.POST, param,
			RefreshTokenResponseDto.class).getBody();
	}

}
