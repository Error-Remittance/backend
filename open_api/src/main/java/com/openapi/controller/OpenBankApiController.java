package com.openapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.openapi.dto.BankRequestToken;
import com.openapi.dto.BankResponseToken;
import com.openapi.service.OpenBankService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
class OpenBankApiController {

	private final OpenBankService openBankService;

	/**
	 * 토큰요청
	 * @param model
	 * @return
	 */
	@GetMapping("/auth/openbank/callback")
	public String getToken(BankRequestToken bankRequestToken, Model model) {
		BankResponseToken token = openBankService.requestToken(bankRequestToken);
		model.addAttribute("bankReponseToken", token);
		log.info("bankReponseToken={}", token);
		return "v1/bank";
	}
}
