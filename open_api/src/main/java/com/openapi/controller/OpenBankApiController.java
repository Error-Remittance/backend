package com.openapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openapi.dto.oauth.IssueTokenRequestDto;
import com.openapi.dto.oauth.IssueTokenResponseDto;
import com.openapi.service.OpenBankService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
// @Controller
class OpenBankApiController {

	private final OpenBankService openBankService;

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
	@Value("${openbank.useCode}")
	private String useCode;
	@Value("${openbank.client-id}")
	private String clientId;
	@Value("${openbank.client-secret}")
	private String client_secret;

	@Value("${openbank.access-token}")
	private String access_token;

	private String redirect_uri = "http://localhost:8080/auth/openbank/callback";

	// /**
	//  * 토큰요청
	//  * @param model
	//  * @return
	//  */
	// @GetMapping("/auth/openbank/callback")
	// public String getToken(BankRequestToken bankRequestToken, Model model) {
	// 	BankResponseToken token = openBankService.requestToken(bankRequestToken);
	// 	model.addAttribute("bankReponseToken", token);
	// 	log.info("bankReponseToken={}", token);
	// 	return "v1/bank";
	// }

	@ResponseBody
	@GetMapping("/auth/openbank/callback")
	public String getToken(IssueTokenRequestDto issueTokenRequestDto) {
		IssueTokenResponseDto token = openBankService.requestToken(issueTokenRequestDto);
		return "access-token : " + token.getAccess_token() +
			"\nrefresh-tokne : " + token.getRefresh_token() +
			"\nuser_seq_no : " + token.getUser_seq_no();
	}





}
