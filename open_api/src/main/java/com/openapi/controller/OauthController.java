package com.openapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.component.common.CommonResponseMaker;
import com.openapi.dto.oauth.AuthorizationResponseDto;
import com.openapi.dto.oauth.IssueTokenRequestDto;
import com.openapi.dto.oauth.IssueTokenResponseDto;
import com.openapi.service.OauthApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OauthController {

	private final CommonResponseMaker commonResponseMaker;
	private final OauthApiService oauthApiService;
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
}
