package com.bank.openbank.dto.account;

import lombok.Data;

@Data
public class InquiryBalanceRequestDto {
	private String bank_tran_id; // 은행거래고유번호
	private String fintech_use_num; // 핀테크 이용번호
	private String tran_dtime; // 요청일시

	public void setInquiryBalanceRequestDto(String bank_tran_id, String tran_dtime) {
		this.bank_tran_id = bank_tran_id;
		this.tran_dtime = tran_dtime;
	}
}
