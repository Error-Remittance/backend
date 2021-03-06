package com.bank.api.transaction.controller;

import com.bank.api.transaction.dto.GetTransactionListResponseDto;
import com.bank.api.transaction.service.TransactionService;
import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.dto.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TransactionController {

	private final TransactionService transactionService;
	private final CommonResponseMaker commonResponseMaker;

	/**
	 * 아마 거래 내역 조회?
	 * @param accountId
	 * @param year
	 * @param month
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/transactions")
	public CommonResponse<GetTransactionListResponseDto> GetTransactionListPerMonth(
		@RequestParam final long accountId, @RequestParam final int year, @RequestParam final int month)
		throws IOException {

		GetTransactionListResponseDto responseDto = GetTransactionListResponseDto.of(
			transactionService.getTransactionListPerMonth(accountId, year, month));

		return commonResponseMaker.makeSucceedCommonResponse(responseDto);
	}
}
