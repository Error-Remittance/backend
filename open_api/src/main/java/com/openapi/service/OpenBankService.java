package com.openapi.service;

import org.springframework.stereotype.Service;

import com.openapi.api.OpenBankApiClient;
import com.openapi.dto.AuthrozationResponseDto;
import com.openapi.dto.BankRequestToken;
import com.openapi.dto.BankResponseToken;
import com.openapi.dto.RefreshTokenRequestDto;
import com.openapi.dto.RefreshTokenResponseDto;

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

	public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto){
		return openBankApiClient.refreshToken(refreshTokenRequestDto);
	}

}
