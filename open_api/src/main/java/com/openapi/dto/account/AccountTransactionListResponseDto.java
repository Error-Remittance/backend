package com.openapi.dto.account;

import java.util.List;

import com.openapi.dto.user.AccountDto;

import lombok.Data;

@Data
public class AccountTransactionListResponseDto {
	private String api_tran_id; // 거래고유번호(API)
	private int api_tran_dtm; // 거래일시(밀리세컨드) int, long 등 뭘 쓸지 고민해야함
	private String rsp_code; // 응답 코드(API)
	private String rsp_message; // 응답 메세지(API)
	private String bank_tran_id; // 거래고유번호(참가은행)
	private String bank_tran_date; // 거래일자(참가은행)
	private String bank_code_tran; // 응답 코드를 부여한 참가 은행.표준코드
	private String bank_rsp_code; // 응답코드(참가은행)
	private String bank_rsp_message; // 응답메세지(참가은행)
	private String bank_name; // 개설기관명
	private String savings_bank_name; // 개별저축은행명
	private String fintech_use_num; // 핀테크이용번호
	private int balance_amt; // 계좌잔액
	private int page_record_cnt; // 현재 페이지 레코드 건수
	private String next_page_yn; // 다음 페이지 존재 여부
	private String befor_inquiry_trace_info; // 직전 조회 추적 정보
	private List<TransactionDto> res_list;
}
