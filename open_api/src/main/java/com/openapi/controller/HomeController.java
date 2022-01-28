package com.openapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.dto.CommonResponse;
import com.openapi.service.OauthApiService;
import com.openapi.service.OpenBankUtil;
import com.openapi.dto.oauth.RefreshTokenRequestDto;
import com.openapi.dto.oauth.RefreshTokenResponseDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final OpenBankUtil openBankUtil;
	private final CommonResponseMaker commonResponseMaker;
	private final OauthApiService oauthApiService;
	/**
	 * clientID = 03366178-450b-46af-a2bf-8af076571fec
	 * http://localhost:8080/auth/openbank/callback
	 * Client Secret = c9b400bd-63dc-45a4-bc84-9d2dad8efa9c
	 *
	 * 토큰 발급 요청 주소(POST)
	 * https://testapi.openbanking.or.kr/oauth/2.0/token
	 * code <authorization_code> Y 사용자인증 성공 후 획득한 Authorization Code
	 *
	 * client_id <client_id> (Max: 40 bytes) Y 오픈뱅킹에서 발급한 이용기관 앱의 Client ID
	 *
	 * client_secret <client_secret> (Max: 40 bytes) Y 오픈뱅킹에서 발급한 이용기관 앱의 Client Secret
	 *redirect_uri <callback_uri> Y
	 *
	 * Access Token을 전달받을 Callback URL
	 *
	 * (Authorization Code 획득 시 요청했던 Callback URL)
	 * grant_type
	 */

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
		RefreshTokenResponseDto refreshTokenResponseDto = oauthApiService.refreshToken(requestDto);
		return commonResponseMaker.makeSucceedCommonResponse(refreshTokenResponseDto);
	}

}
