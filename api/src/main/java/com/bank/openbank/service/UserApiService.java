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

import com.bank.openbank.dto.user.UserInfoRequestDto;
import com.bank.openbank.dto.user.UserInfoResponseDto;
import com.bank.openbank.dto.user.UserUnlinkRequestDto;
import com.bank.openbank.dto.user.UserUnlinkResponseDto;

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
	private final String base_url = "https://testapi.openbanking.or.kr/v2.0";

	/**
	 * 사용자정보조회
	 * 필요 파라미터 : user_seq_no(사용자 일련 번호)
	 */
	public UserInfoResponseDto requestUserInfo(UserInfoRequestDto userInfoRequestDto) {
		String url = base_url + "/user/me";
		HttpEntity<Object> openBankUserInfoRequest = new HttpEntity<>(openBankUtil.makeAccessTokenHeader(accessToken));
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
			.queryParam("user_seq_no", userInfoRequestDto.getUser_seq_no())
			.build();

		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
			openBankUserInfoRequest, UserInfoResponseDto.class).getBody();
	}

	/**
	 * 사용자 탈퇴
	 * 필요 파라미터 : client_use_code(이용기관코드) , user_seq_no(사용자 일련번호)
	 */
	public UserUnlinkResponseDto unlinkUser(UserUnlinkRequestDto userUnlinkRequestDto) {
		userUnlinkRequestDto.setUserUnlinkRequestDto(useCode);
		log.info("user_seq_no : {}", userUnlinkRequestDto.getUser_seq_no());
		log.info("client_use_code : {}", userUnlinkRequestDto.getClient_user_code());

		//header 설정
		HttpHeaders httpHeaders = openBankUtil.makeAccessTokenHeader(accessToken);
		httpHeaders.add("Content_Type", "application/json;charset=UTF-8");

		// body 설정
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("client_use_code", userUnlinkRequestDto.getClient_user_code());
		parameters.add("user_seq_no", userUnlinkRequestDto.getUser_seq_no());

		HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<>(parameters, httpHeaders);

		return restTemplate.exchange(base_url + "/user/close", HttpMethod.POST,
			param, UserUnlinkResponseDto.class)
			.getBody();

	}

}
