package com.openapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.openapi.dto.account.AccountListRequestDto;
import com.openapi.dto.account.AccountListResponseDto;
import com.openapi.dto.account.InquiryBalanceRequestDto;
import com.openapi.dto.account.InquiryBanlaceResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountApiService {

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
	private final String base_url = "https://testapi.openbanking.or.kr";

	/**
	 * 사용자 계좌 조회
	 */
	public AccountListResponseDto requestUserAccountList(AccountListRequestDto accountListRequestDto) {
		// Default 값 세팅
		accountListRequestDto.setAccountListRequestDto("Y", "D");

		String url = base_url + "/v2.0/account/list";
		HttpEntity<String> openBankAcountSerchRequest = new HttpEntity<>(
			openBankUtil.makeAccessTokenHeader(accessToken));
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
			.queryParam("user_seq_no", accountListRequestDto.getUser_seq_no())
			.queryParam("include_cancel_yn", accountListRequestDto.getInclude_cancel_yn())
			.queryParam("sort_order", accountListRequestDto.getSort_order())
			.build();

		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, openBankAcountSerchRequest,
			AccountListResponseDto.class).getBody();
	}

	/**
	 * 잔액조회
	 * 핀테크 이용 번호?
	 */
	public InquiryBanlaceResponseDto requestBalance(InquiryBalanceRequestDto inquiryBalanceRequestDto) {
		String url = base_url + "/account/balance/fin_num";
		HttpEntity<String> balance = new HttpEntity<>(openBankUtil.makeAccessTokenHeader(accessToken));
		inquiryBalanceRequestDto.setInquiryBalanceRequestDto(
			openBankUtil.getRandomNumber(inquiryBalanceRequestDto.getBank_tran_id()),
			openBankUtil.getTransTime());
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
			.queryParam("bank_tran_id", inquiryBalanceRequestDto.getBank_tran_id())
			.queryParam("fintech_use_num", inquiryBalanceRequestDto.getFintech_use_num())
			.queryParam("tran_dtime", inquiryBalanceRequestDto.getTran_dtime())
			.build();

		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, balance, InquiryBanlaceResponseDto.class)
			.getBody();
	}
}
