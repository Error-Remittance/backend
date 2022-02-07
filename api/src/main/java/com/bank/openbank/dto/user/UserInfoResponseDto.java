package com.bank.openbank.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class UserInfoResponseDto {
	private String api_tran_id; // 거래고유번호(API)
	private String api_tran_dtm; // 거래일시(밀리세컨드) int, long 등 뭘 쓸지 고민해야함
	private String rsp_code; // 응답 코드(API)
	private String rsp_message; // 응답 메세지(API)
	private String user_seq_no; // 사용자 일련번호
	private String user_ci; // CI(Connect Info)
	private String user_name; // 고객명
	// 선택부의 경우, 특정 자격 요건을 갖춘 이용기관에 선별적 제공(전자금융업자 등)
	private String user_info; // (선택) 생년월일
	private String user_gender; // (선택) 성별
	private String user_cell_no; // (선택) 휴대폰 번호
	private String user_email; // (선택) 이메일 주소
	private int res_cnt; // 등록된 계좌 개수
	private List<AccountDto> res_list;
}
