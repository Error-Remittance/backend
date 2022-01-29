package com.openapi.dto.transfer;

import lombok.Data;

@Data
public class WithdrawRequestDto {
	private String bank_tran_id; // 은행 거래 고유번호
	private String cntr_account_type; // 약정계좌/ 계정 구분 N:계좌, C: 계정
	private String cntr_account_num; // 약정 계좌/계정 번호
	private String dps_print_content; // 입금계좌인자내역
	private String fintech_use_num; // 출금계좌핀테크이용번호
	private int tran_amt; // 거래금액
	private String tran_dtime; // 요청일시
	private String req_client_name; // 요청고객성명
	private String req_client_num; // 요청고객회원번호
	private String transfer_purpose; // 이체 용도

}
