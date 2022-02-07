package com.bank.openbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.dto.CommonResponse;
import com.bank.openbank.dto.oauth.AuthorizationResponseDto;
import com.bank.openbank.dto.oauth.IssueTokenRequestDto;
import com.bank.openbank.dto.oauth.IssueTokenResponseDto;
import com.bank.openbank.dto.oauth.RefreshTokenRequestDto;
import com.bank.openbank.dto.oauth.RefreshTokenResponseDto;
import com.bank.openbank.service.OauthApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OpenOauthController {

	private final CommonResponseMaker commonResponseMaker;
	private final OauthApiService oauthApiService;

	@GetMapping("/auth/openbank/callback")
	public String getInitialToken(AuthorizationResponseDto authorizationResponseDto) {
		log.info("code : {}", authorizationResponseDto.getCode());
		log.info("scope : {}", authorizationResponseDto.getScope());
		log.info("client_info : {}", authorizationResponseDto.getClient_info());
		log.info("state : {}", authorizationResponseDto.getState());
		IssueTokenRequestDto issueTokenRequestDto = new IssueTokenRequestDto();
		issueTokenRequestDto.setCode(authorizationResponseDto.getCode());
		IssueTokenResponseDto token = oauthApiService.requestToken(issueTokenRequestDto);
		return "access-token : " + token.getAccess_token() +
			"\nrefresh-tokne : " + token.getRefresh_token() +
			"\nuser_seq_no : " + token.getUser_seq_no();
	}

	@GetMapping("/auth/openbank/callback/init")
	public String getToken(AuthorizationResponseDto authorizationResponseDto) {
		log.info("code : {}", authorizationResponseDto.getCode());
		log.info("scope : {}", authorizationResponseDto.getScope());
		log.info("client_info : {}", authorizationResponseDto.getClient_info());
		log.info("state : {}", authorizationResponseDto.getState());
		IssueTokenRequestDto issueTokenRequestDto = new IssueTokenRequestDto();
		issueTokenRequestDto.setCode(authorizationResponseDto.getCode());
		IssueTokenResponseDto token = oauthApiService.requestToken(issueTokenRequestDto);
		return "access-token : " + token.getAccess_token() +
			"\nrefresh-tokne : " + token.getRefresh_token() +
			"\nuser_seq_no : " + token.getUser_seq_no();
	}

	@PostMapping("/auth/refresh")
	public CommonResponse<RefreshTokenResponseDto> refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
		log.info("refresh_token : {}", refreshTokenRequestDto.getRefresh_token());
		RefreshTokenRequestDto refreshTokenRequestDto1 = new RefreshTokenRequestDto();
		refreshTokenRequestDto1.setRefresh_token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDAyOTU2Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NTIzMzMwNzUsImp0aSI6IjgxODRhZmQyLWVhNjktNGFhYS1iZmMyLTFkZDQ4MWJkYWUyYyJ9.wpGL1P66H2gIMzjtWfWQEIH7q32k6TcpY-AdP6Tv5Kk");
		return commonResponseMaker.makeSucceedCommonResponse(oauthApiService.refreshToken(refreshTokenRequestDto1));
	}
}
