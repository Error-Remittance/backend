package com.bank.api.account.service;

import java.io.IOException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.bank.component.account.repository.AccountRepository;
import com.bank.component.account.repository.BankRepository;
import com.bank.component.account.vo.GetAccountListResponseVo;
import com.bank.component.account.vo.UpdateAllAccountRequestVo;
import com.bank.component.account.vo.UpdateAllAccountResponseVo;
import com.bank.component.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
	private final UserRepository userRepository;
	private final AccountRepository accountRepository;
	private final BankRepository bankRepository;
	private final RestTemplate restTemplate;
	private final PasswordEncoder passwordEncoder;

	// @Value("${wooribank.appkey}")
	private String appkey;

	// @Value("${wooribank.url.wooribank-open-api}")
	private String openApiUrl;


	@Transactional
	public UpdateAllAccountResponseVo updateAllAccounts(UpdateAllAccountRequestVo requestVo) throws IOException {
		return null;
	}

	@Transactional(readOnly = true)
	public GetAccountListResponseVo getAccountList(final String userId, final String password) throws IOException {
		return null;
	}

	// @Transactional
	// public UpdateAllAccountResponseVo updateAllAccounts(UpdateAllAccountRequestVo requestVo) throws IOException {
	//
	// 	final ObjectMapper objectMapper = new ObjectMapper();
	//
	// 	final String requestBody = "{\n" +
	// 		"  \"dataHeader\": {\n" +
	// 		"    \"UTZPE_CNCT_IPAD\": \"\",\n" +
	// 		"    \"UTZPE_CNCT_MCHR_UNQ_ID\": \"\",\n" +
	// 		"    \"UTZPE_CNCT_TEL_NO_TXT\": \"\",\n" +
	// 		"    \"UTZPE_CNCT_MCHR_IDF_SRNO\": \"\",\n" +
	// 		"    \"UTZ_MCHR_OS_DSCD\": \"\",\n" +
	// 		"    \"UTZ_MCHR_OS_VER_NM\": \"\",\n" +
	// 		"    \"UTZ_MCHR_MDL_NM\": \"\",\n" +
	// 		"    \"UTZ_MCHR_APP_VER_NM\": \"\"\n" +
	// 		"  },\n" +
	// 		"  \"dataBody\": {}\n" +
	// 		"}";
	//
	// 	// header를 만들어
	// 	HttpHeaders headers = new HttpHeaders();
	// 	headers.add("Content-Type", "application/json");
	// 	headers.add("appkey", appkey);
	//
	// 	final String requestUrl = openApiUrl + "/finance/getIndivAllAccInfo";
	//
	// 	final ResponseEntity<String> responseEntity = restTemplate.exchange(
	// 		requestUrl, HttpMethod.POST, new HttpEntity<String>(requestBody, headers), String.class);
	//
	// 	objectMapper.writeValueAsString(responseEntity.getBody());
	//
	// 	Gson gson = new Gson();
	// 	LoadAccountDataBody loadAccountDataBody = gson.fromJson(responseEntity.getBody(), LoadAccountDataBody.class);
	//
	// 	// user를 가져와
	// 	final Optional<AppUser> userOptional = userRepository.findTopByUserId(requestVo.getUserId());
	//
	// 	// 없으면 에러를 내
	// 	final AppUser findUser = userOptional.orElseThrow(() ->
	// 		new CommonException(ResponseCode.USER_NOT_EXISTED));
	//
	// 	// 패스워드를 비교해
	// 	if (!passwordEncoder.matches(requestVo.getPassword(), findUser.getPassword())) {
	// 		throw new CommonException(ResponseCode.INVALID_PASSWORD);
	// 	}
	//
	// 	final Optional<Bank> bankOptional = bankRepository.findTopByName("우리은행");
	//
	// 	final Bank findBank = bankOptional.orElseThrow(() ->
	// 		new CommonException(ResponseCode.BANK_NOT_EXISTED));
	//
	// 	for (int index = 0; index < loadAccountDataBody.getDataBody().getGRID_CNT(); index++) {
	// 		final Account account = new Account(
	// 			loadAccountDataBody.getDataBody().getGRID().get(index).getACNO(),
	// 			loadAccountDataBody.getDataBody().getGRID().get(index).getPRD_NM(),
	// 			loadAccountDataBody.getDataBody().getGRID().get(index).getPBOK_BAL(),
	// 			findUser, findBank);
	//
	// 		account.setUser(findUser);
	//
	// 		accountRepository.save(account);
	// 	}
	//
	// 	return UpdateAllAccountResponseVo.builder().accountList(findUser.toAccountList()).build();
	// }
	//
	// @Transactional(readOnly = true)
	// public GetAccountListResponseVo getAccountList(final String userId, final String password) throws IOException {
	//
	// 	final Optional<AppUser> userOptional = userRepository.findTopByUserId(userId);
	//
	// 	final AppUser user = userOptional.orElseThrow(() ->
	// 		new CommonException(ResponseCode.USER_NOT_EXISTED));
	//
	// 	if (!passwordEncoder.matches(password, user.getPassword())) {
	// 		throw new CommonException(ResponseCode.INVALID_PASSWORD);
	// 	}
	//
	// 	return GetAccountListResponseVo.builder().accountList(user.toAccountList()).build();
	// }
}
