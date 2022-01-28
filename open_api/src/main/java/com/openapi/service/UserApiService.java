package com.openapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.openapi.dto.user.UserInfoRequestDto;
import com.openapi.dto.user.UserInfoResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserApiService {

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
	 * 사용자정보조회
	 */
	public UserInfoResponseDto requestUserInfo(UserInfoRequestDto userInfoRequestDto) {
		String url = base_url + "/v2.0/user/me";
		HttpEntity<Object> openBankUserInfoRequest = new HttpEntity<>(openBankUtil.makeAccessTokenHeader(accessToken));
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
			.queryParam("user_seqq_no", userInfoRequestDto.getUser_seq_no())
			.build();

		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
			openBankUserInfoRequest, UserInfoResponseDto.class).getBody();
	}
}
