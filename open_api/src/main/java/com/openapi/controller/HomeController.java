package com.openapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.dto.CommonResponse;
import com.openapi.api.OpenBankUtil;
import com.openapi.dto.oauth.RefreshTokenRequestDto;
import com.openapi.dto.oauth.RefreshTokenResponseDto;
import com.openapi.service.OpenBankService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final OpenBankUtil openBankUtil;
	private final CommonResponseMaker commonResponseMaker;
	private final OpenBankService openBankService;

	@Value("${openbank.client-id}")
	private String clientId;

	@Value("${openbank.access-token}")
	private String accessToken;

	@Value("${openbank.refresh-token}")
	private String refreshToken;

	@GetMapping("/")
	public String index() {
		return "/index";
	}

	// @ResponseBody
	// @GetMapping("/auth/openbank/callback")
	// public CommonResponse<AuthrozationResponseDto> authorizeClient(AuthrozationResponseDto responseDto){
	// 	System.out.println("client info = " + responseDto.getClient_info());
	// 	System.out.println("code = " + responseDto.getCode());
	// 	return commonResponseMaker.makeSucceedCommonResponse(responseDto);
	// }

	// @ResponseBody
	// @GetMapping("/auth/openbank/callback")
	// public CommonResponse<BankResponseToken> requestToken( ){
	// 	BankRequestToken requestToken = new BankRequestToken();
	// 	requestToken.setCode("37FgTARgaLo6gVOMdrDSNdz0ftFAGw");
	// 	BankResponseToken bankResponseToken = openBankService.requestToken(requestToken);
	// 	return commonResponseMaker.makeSucceedCommonResponse(bankResponseToken);
	// }

	@ResponseBody
	@GetMapping("/token/refresh")
	public CommonResponse<RefreshTokenResponseDto> refreshToken(RefreshTokenRequestDto requestDto) {
		RefreshTokenResponseDto refreshTokenResponseDto = openBankService.refreshToken(requestDto);
		return commonResponseMaker.makeSucceedCommonResponse(refreshTokenResponseDto);
	}

}
