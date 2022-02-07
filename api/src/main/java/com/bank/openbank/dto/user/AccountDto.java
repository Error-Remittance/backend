package com.bank.openbank.dto.user;

import lombok.Data;

@Data
public class AccountDto {
	// N(14) 의 의미를 알아봐야 겠다.
	private String fintech_use_num; // 핀테크이용번호
	private String acoount_alias; // 계좌 별명(alias)
	private String bank_code_std; // 출금(개설) 기관.표준코드
	private String bank_code_sub; // 출금(개설) 기관.점별코드
	private String bank_name; // 출금(개설) 기관명
	private String savings_bank_name; // 개별저축은행명
	private String account_num; // 계좌번호
	private String account_num_masked; // 마스킹된 출력용 계좌번호
	private String account_seq; // 회차번호
	private String account_holder_name; // 계좌 예금주명
	private String account_holder_type; // 계좌구분(P:개인)
	private String account_type; // 계좌종류 1:수시입출금, 2:예적금 6: 수익증권, T:종합계좌
	private String inquiry_agree_yn; // 조회서비스 동의여부
	private String inquiry_agree_dtime; // 조회서비스 동의 일시
	private String tranfer_agree_yn; // 출금서비스 동의 여부
	private String transfer_agree_dtime; // 출금서비스 동의 일시
	private String payer_num; // 납부자 번호
}
