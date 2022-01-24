package com.openapi.service;

import org.springframework.stereotype.Service;

import com.openapi.api.OpenBankApiClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OpenBankService {

	private final OpenBankApiClient openBankApiClient;

}
