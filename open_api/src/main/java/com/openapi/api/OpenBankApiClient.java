package com.openapi.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.openapi.dto.oauth.AuthorizationRequestDto;
import com.openapi.dto.oauth.AuthrozationResponseDto;
import com.openapi.dto.oauth.IssueTokenRequestDto;
import com.openapi.dto.oauth.IssueTokenResponseDto;
import com.openapi.dto.oauth.RefreshTokenRequestDto;
import com.openapi.dto.oauth.RefreshTokenResponseDto;
import com.openapi.dto.user.UserInfoRequestDto;
import com.openapi.dto.user.UserInfoResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenBankApiClient {

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
	 * 아직 시도는 안해봄₩
	 */
	public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
		log.info("refresh token start");
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

		return restTemplate.exchange(base_url + "/oauth/2.0/token", HttpMethod.POST, param,
			RefreshTokenResponseDto.class).getBody();
	}

	/**
	 * 시도해 봐야 함
	 * @param userInfoRequestDto
	 * @return
	 */
	public UserInfoResponseDto requestUserInfo(UserInfoRequestDto userInfoRequestDto) {
		log.info("request User info start");
		log.info("user_seq_no : {}" , userInfoRequestDto.getUser_seq_no());

		// Header 설정
		HttpHeaders httpHeaders = makeAccessTokenHeader(accessToken);
		log.info("Authorization : {}",httpHeaders.get("Authorization"));

		// Body 설정 -> 왜 파라미터로 넘기면 안될까?
		// MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		// parameters.add("user_seq_no", userInfoRequestDto.getUser_seq_no());

		HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<>(null, httpHeaders);

		return restTemplate.exchange(base_url + "/v2.0/user/me?user_seq_no=" + userInfoRequestDto.getUser_seq_no() , HttpMethod.GET, param, UserInfoResponseDto.class).getBody();

	}

	/**
	 * 헤더에 엑세스 토큰넣기
	 * @param access_token
	 * @return
	 */
	public HttpHeaders makeAccessTokenHeader(String access_token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer " + access_token);
		return httpHeaders;
	}
}
