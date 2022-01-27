package com.openapi.dto.account;

public class AccountTransactionListRequestDto {
	private String bank_tran_id; // 은행거래고유번호 required
	private String fintech_use_num; // 핀테크이용번호 required
	private String inquiry_type; // 조회구분코드(A:All, I:입금, O:출금) required
	private String inquiry_base; // 조회기준코드((D:일자, T:시간) required
	private String from_data; // 날짜인데 자료형 확인해야함 조회시작일자 // required
	private String from_time; // 조회시작시간
	private String to_date; // 조회종료일자 // required
	private String to_time; // 조회종료시간
	private String sort_order; // 정렬순서 required
	private String tran_dtime; // 요청일시 required
	private String befor_inquiry_trace_info; // 직전조회추적정보
}
