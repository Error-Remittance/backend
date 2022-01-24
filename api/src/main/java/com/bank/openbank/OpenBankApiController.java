package com.bank.openbank;

import com.bank.openbank.api.OpenBankUtil;
import com.bank.openbank.dto.AccountSearchRequestDto;
import com.bank.openbank.dto.AccountTransferRequestDto;
import com.bank.openbank.dto.AccountTransferResponseDto;
import com.bank.openbank.dto.BankAccountSearchResponseDto;
import com.bank.openbank.dto.BankBalanceRequestDto;
import com.bank.openbank.dto.BankRequestToken;
import com.bank.openbank.dto.BankResponseToken;
import com.bank.openbank.service.OpenBankService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class OpenBankApiController {
	private final HttpSession session;
	private final OpenBankUtil openBankUtil;
	/**
	 * clientID = 	318b30f8-87cc-4e10-aff2-027ebd3e3b3a
	 * http://localhost:8080/auth/openbank/callback
	 * Client Secret = 5081186a-2d57-49cb-ab45-03dc853079b8
	 *
	 * 토큰 발급 요청 주소(POST)
	 * https://testapi.openbanking.or.kr/oauth/2.0/token
	 * code <authorization_code> Y 사용자인증 성공 후 획득한 Authorization Code
	 *
	 * client_id <client_id> (Max: 40 bytes) Y 오픈뱅킹에서 발급한 이용기관 앱의 Client ID
	 *
	 * client_secret <client_secret> (Max: 40 bytes) Y 오픈뱅킹에서 발급한 이용기관 앱의 Client Secret
	 *redirect_uri <callback_uri> Y
	 *
	 * Access Token을 전달받을 Callback URL
	 *
	 * (Authorization Code 획득 시 요청했던 Callback URL)
	 * grant_type
	 */
	@Value("${openbank.useCode}")
	private String useCode;
	@Value("${openbank.client-id}")
	private String clientId;
	@Value("${openbank.client-secret}")
	private String client_secret;

	@Value("${openbank.access-token}")
	private String access_token;
	private String redirect_uri = "http://localhost:8080/auth/openbank/callback";
	private final OpenBankService openBankService;

	/**
	 * 토큰요청
	 * @param model
	 * @return
	 */
	@GetMapping("/auth/openbank/callback")
	public String getToken(BankRequestToken bankRequestToken, Model model) {
		BankResponseToken token = openBankService.requestToken(bankRequestToken);
		model.addAttribute("bankReponseToken", token);
		log.info("bankReponseToken={}", token);
		return "v1/bank";
	}

	/**
	 * 계좌조회
	 * dto 만들기
	 * @param accountSearchRequestDto
	 * @param model
	 * @return
	 */
	@GetMapping("/acount/list")
	public String searchAcountList(AccountSearchRequestDto accountSearchRequestDto, Model model) {
		BankAccountSearchResponseDto account = openBankService.findAccount(accountSearchRequestDto);
		model.addAttribute("bankAccounts", account);
		model.addAttribute("useCode", useCode);
		model.addAttribute("access_token", access_token);
		return "v1/accountList";
	}

	/**
	 * 잔액조회
	 */
	@GetMapping("/balance")
	public String searchBalance(String access_token, BankBalanceRequestDto bankBalanceRequestDto, Model model) {
		model.addAttribute("accountBalance", openBankService.findBalance(access_token, bankBalanceRequestDto));
		return "v1/balance";
	}

	/**
	 * 계좌이체
	 * 계좌이체 처리 테스트에 등록된 값만 계좌이체가능!!
	 */
	@GetMapping("/transfer")
	public String openTransfer(Model model, String bank_tran_id, String access_token, String fintech_use_num,
		String account_num, String req_client_name) {
		/**
		 * 20000, 100000원만 등록되어있음
		 */
		//계좌번호 마스킹된값 제거(계좌번호 보여주는건 계약된 사용자만가능(그래서 마스킹된 3자리 잘라서 보내주고 클라이언트에서 3자리 더해줌
		model.addAttribute("token", access_token);
		model.addAttribute("transferForm",
			new AccountTransferRequestDto(openBankUtil.getRandomNumber(bank_tran_id), fintech_use_num, req_client_name,
				openBankUtil.trimAccountNum(account_num, account_num.length()),
				openBankUtil.trimAccountNum(account_num, account_num.length())));
		return "v1/transferForm";
	}

	@PostMapping("/transfer")
	public @ResponseBody
	AccountTransferResponseDto transfer(String access_token, AccountTransferRequestDto accountTransferRequestDto) {
		return openBankService.accountTransfer(access_token, accountTransferRequestDto);
	}

}