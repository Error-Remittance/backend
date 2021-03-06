package com.bank.openbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.dto.CommonResponse;
import com.bank.openbank.dto.account.AccountListRequestDto;
import com.bank.openbank.dto.account.AccountListResponseDto;
import com.bank.openbank.dto.account.AccountTransactionListRequestDto;
import com.bank.openbank.dto.account.AccountTransactionListResponseDto;
import com.bank.openbank.dto.account.InquiryBalanceRequestDto;
import com.bank.openbank.dto.account.InquiryBalanceResponseDto;
import com.bank.openbank.service.AccountApiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OpenAccountController {

	private final CommonResponseMaker commonResponseMaker;
	private final AccountApiService accountApiService;

	@GetMapping("/account/list/{user_seq_no}")
	public CommonResponse<AccountListResponseDto> getUserAccountList(@PathVariable String user_seq_no) {
		AccountListRequestDto accountListRequestDto = new AccountListRequestDto();
		accountListRequestDto.setUser_seq_no(user_seq_no);
		return commonResponseMaker.makeSucceedCommonResponse(
			accountApiService.requestUserAccountList(accountListRequestDto));
	}

	/**
	 * 잔액조회
	 *
	 * fintech_use_num : 핀테크 이용번호 : 사용자가 오픈뱅킹에 계좌 등록 시 발급되는 가상의 계좌식별번호
	 */
	@GetMapping("/account/{fintech_use_num}/balance")
	public CommonResponse<InquiryBalanceResponseDto> requestBalance(@PathVariable String fintech_use_num) {
		InquiryBalanceRequestDto inquiryBalanceRequestDto = new InquiryBalanceRequestDto();
		inquiryBalanceRequestDto.setFintech_use_num(fintech_use_num);
		return commonResponseMaker.makeSucceedCommonResponse(
			accountApiService.requestBalance(inquiryBalanceRequestDto));
	}

	/**
	 * 계좌내역조회
	 * request 포함 값 : fintech_use_num, from_date, to_date "yyyyMMdd" 형식
	 */
	@GetMapping("/account/transaction_list")
	public CommonResponse<AccountTransactionListResponseDto> requestTransactionList(
		AccountTransactionListRequestDto accountTransactionListRequestDto) {
		return commonResponseMaker.makeSucceedCommonResponse(
			accountApiService.requestTransactionList(accountTransactionListRequestDto));
	}

}
