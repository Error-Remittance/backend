package com.bank.openbank.dto.transfer;

import lombok.Data;

@Data
public class WithdrawResponseDto {
	private String api_tran_id; // 거래고유번호(API)
	private String api_tran_dtm; // 거래일시(밀리세컨드)
	private String rsp_code; // 응답코드(API)
	private String rsp_message; // 응답메세지(API)
	private String dps_bank_code_std; // 입금기관.표준코드
	private String dps_bank_code_sub; // 입금기관.점별코드
	private String dps_bank_name; // 입금기관명
	private String dps_account_num_masked; // 입금계좌번호(출력용)
	private String dps_print_content; //입금계좌인자내역
	private String dps_account_holder_name; // 수취인 성명
	private String bank_tran_id; // 거래고유번호(참가은행)
	private String bank_tran_date; // 거래일자(참가은행)
	private String bank_code_tran; // 응답코드를 부여한 참가은행.표준코드
	private String bank_rsp_code; // 응담코드(참가은행)
	private String bank_rsp_message; // 응답메세지(참가은행)
	private String fintech_use_num; // 츨금계좌핀테크이용번호
	private String account_alias; // 출금계좌별명(Alias)
	private String bank_code_std; // 츨금(개설)기관.표준코드
	private String bank_code_sub; // 출금(개설)기관.점별코드
	private String bank_name; // 출금(개설)기관명
	private String savings_bank_name; // 개별저축은행명
	private String account_num_masked; // 출금계좌번호(출력용)
	private String print_content; // 출금계좌인자내역
	private String account_holder_name; // 송금인 성명
	private int tran_amt; // 거래 금액
	private String wd_limit_remain_amt; // 출금한도잔여금액

}
