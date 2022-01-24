package com.openapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.dto.CommonResponse;
import com.openapi.api.OpenBankUtil;
import com.openapi.dto.AuthorizationRequestDto;
import com.openapi.dto.AuthrozationResponseDto;
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

	@GetMapping("/")
	public String index(){
		return "/index";
	}

	@ResponseBody
	@GetMapping("/auth/openbank/callback")
	public CommonResponse<AuthrozationResponseDto> authorizeClient(AuthrozationResponseDto responseDto){
		System.out.println("client info = " + responseDto.getClient_info());
		System.out.println("code = " + responseDto.getCode());
		return commonResponseMaker.makeSucceedCommonResponse(responseDto);
	}

}
