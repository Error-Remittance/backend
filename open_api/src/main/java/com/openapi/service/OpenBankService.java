package com.openapi.service;

import org.springframework.stereotype.Service;

import com.openapi.api.OpenBankApiClient;
import com.openapi.dto.AuthrozationResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OpenBankService {

	private final OpenBankApiClient openBankApiClient;

	public AuthrozationResponseDto authorizeClient(){
		return openBankApiClient.authrizationClient();
	}

}
