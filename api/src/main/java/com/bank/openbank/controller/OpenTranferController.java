package com.bank.openbank.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.component.common.CommonResponseMaker;
import com.bank.openbank.dto.transfer.AccountTransferRequestDto;
import com.bank.openbank.dto.transfer.AccountTransferResponseDto;
import com.bank.openbank.service.OpenBankUtil;
import com.bank.openbank.service.TransferApiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OpenTranferController {
	private final CommonResponseMaker commonResponseMaker;
	private final TransferApiService transferApiService;
	private final OpenBankUtil openBankUtil;

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
		return transferApiService.requestTransfer(access_token, accountTransferRequestDto);
	}

}
