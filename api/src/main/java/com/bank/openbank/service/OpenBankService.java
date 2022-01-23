package com.bank.openbank.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.bank.openbank.api.OpenBankApiClient;
import com.bank.openbank.dto.AccountSearchRequestDto;
import com.bank.openbank.dto.AccountTransferRequestDto;
import com.bank.openbank.dto.AccountTransferResponseDto;
import com.bank.openbank.dto.BankAccountSearchResponseDto;
import com.bank.openbank.dto.BankBalanceRequestDto;
import com.bank.openbank.dto.BankBalanceResponseDto;
import com.bank.openbank.dto.BankRequestToken;
import com.bank.openbank.dto.BankResponseToken;

@RequiredArgsConstructor
@Service
public class OpenBankService {

	private final OpenBankApiClient openBankApiClient;

	public BankResponseToken requestToken(BankRequestToken bankRequestToken) {
		return openBankApiClient.requestToken(bankRequestToken);
	}

	public BankAccountSearchResponseDto findAccount(AccountSearchRequestDto accountSearchRequestDto) {
		return openBankApiClient.requestAccountList(accountSearchRequestDto);
	}

	public BankBalanceResponseDto findBalance(String access_token, BankBalanceRequestDto bankBalanceRequestDto) {
		return openBankApiClient.requestBalance(bankBalanceRequestDto, access_token);
	}

	public AccountTransferResponseDto accountTransfer(String access_token,
		AccountTransferRequestDto accountTransferRequestDto) {
		return openBankApiClient.requestTransfer(access_token, accountTransferRequestDto);
	}

}
