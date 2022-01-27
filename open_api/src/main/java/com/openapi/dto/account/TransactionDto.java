package com.openapi.dto.account;

import lombok.Data;

@Data
public class TransactionDto {
	private String tran_date; // 거래일자
	private String tran_time; // 거래시간
	private String inout_type; // 입출금구분( 입금, 출금, 지금, 기타)
	private String tran_type; // 거래구분
	private String printed_content; // 통장인자내용
	private int tran_amt; // 거래금액
	private int after_balance_amt; // 거래후 잔액(-금액가능)
	private String branch_name; // 거래점명
}
