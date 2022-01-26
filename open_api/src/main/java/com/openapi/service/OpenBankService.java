package com.openapi.service;

import org.springframework.stereotype.Service;

import com.openapi.api.OpenBankApiClient;
import com.openapi.dto.AuthrozationResponseDto;
import com.openapi.dto.BankRequestToken;
import com.openapi.dto.BankResponseToken;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OpenBankService {

	private final OpenBankApiClient openBankApiClient;

	public AuthrozationResponseDto authorizeClient(){
		return openBankApiClient.authrizationClient();
	}

	public BankResponseToken requestToken(BankRequestToken bankRequestToken) {
		return openBankApiClient.requestToken(bankRequestToken);
	}

}
