package com.openapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.dto.CommonResponse;
import com.openapi.dto.account.AccountListRequestDto;
import com.openapi.dto.account.AccountListResponseDto;
import com.openapi.service.AccountApiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AccountController {

	private final CommonResponseMaker commonResponseMaker;
	private final AccountApiService accountApiService;
	@GetMapping("/account/list/{user_seq_no}")
	public CommonResponse<AccountListResponseDto> getUserAccountList(@PathVariable String user_seq_no) {
		AccountListRequestDto accountListRequestDto = new AccountListRequestDto();
		accountListRequestDto.setUser_seq_no(user_seq_no);
		return commonResponseMaker.makeSucceedCommonResponse(
			accountApiService.requestUserAccountList(accountListRequestDto));
	}
}
