package com.openapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.openapi.dto.transfer.AccountTransferRequestDto;
import com.openapi.dto.transfer.AccountTransferResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransferApiService {

	private final OpenBankUtil openBankUtil;
	private final RestTemplate restTemplate;

	@Value("${openbank.useCode}")
	private String useCode;
	@Value("${openbank.client-id}")
	private String clientId;
	@Value("${openbank.client-secret}")
	private String clientSecret;
	@Value("${openbank.access-token}")
	private String accessToken;

	private final String redirect_uri = "http://localhost:8080/auth/openbank/callback";
	private final String base_url = "https://testapi.openbanking.or.kr/v2.0";

	/**
	 * 계좌이체
	 * 계좌 이체의 경우 test 데이터를 등록하고 잘 이용해보도록
	 */
	public AccountTransferResponseDto requestTransfer(String access_token,
		AccountTransferRequestDto accountTransferRequestDto) {
		String url = base_url + "//transfer/withdraw/fin_num";
		accountTransferRequestDto.setTran_dtime(openBankUtil.getTransTime());
		ResponseEntity<AccountTransferRequestDto> param = new ResponseEntity<>(accountTransferRequestDto,
			openBankUtil.makeAccessTokenHeader(access_token), HttpStatus.OK);
		return restTemplate.exchange(url, HttpMethod.POST, param, AccountTransferResponseDto.class).getBody();
	}
}
