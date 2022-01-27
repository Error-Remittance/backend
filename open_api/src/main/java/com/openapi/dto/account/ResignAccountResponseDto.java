package com.openapi.dto.account;

import lombok.Data;

@Data
public class ResignAccountResponseDto {
	private String api_tran_id; // 거래고유번호(API)
	private int api_tran_dtm; // 거래일시(밀리세컨드) int, long 등 뭘 쓸지 고민해야함
	private String rsp_code; // 응답 코드(API)
	private String rsp_message; // 응답 메세지(API)
	private String bank_tran_id; // 거래고유번호(참가은행)
	private String bank_tran_date; // 거래일자(참가은행)
	private String bank_code_tran; // 응답 코드를 부여한 참가 은행.표준코드
	private String bank_rsp_code; // 응답코드(참가은행)
	private String bank_rsp_message; // 응답메세지(참가은행)
}
