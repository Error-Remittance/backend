package com.bank.api.account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import com.bank.api.account.dto.GetAccountListResponseDto;
import com.bank.api.account.dto.UpdateAllAccountRequestDto;
import com.bank.api.account.dto.UpdateAllAccountResponseDto;
import com.bank.api.account.service.AccountService;
import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.dto.CommonResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController extends AppApiV1Controller {

	private final AccountService accountService;
	private final CommonResponseMaker commonResponseMaker;

	/**
	 * 계좌 내역 update?
	 * @param requestDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/accounts/update")
	public CommonResponse<UpdateAllAccountResponseDto> UpdateAllAccounts(
		@RequestBody UpdateAllAccountRequestDto requestDto) throws IOException {

		UpdateAllAccountResponseDto responseDto = UpdateAllAccountResponseDto.of(
			accountService.updateAllAccounts(requestDto.toVo()));

		return commonResponseMaker.makeSucceedCommonResponse(responseDto);
	}

	/**
	 * 계좌 조회 -> 잔액조회 등?
	 * @param userId
	 * @param password
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/accounts")
	public CommonResponse<GetAccountListResponseDto> GetAccountList(
		@RequestParam final String userId, @RequestParam final String password) throws IOException {

		GetAccountListResponseDto responseDto = GetAccountListResponseDto.of(
			accountService.getAccountList(userId, password));

		return commonResponseMaker.makeSucceedCommonResponse(responseDto);
	}
}
