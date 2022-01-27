package com.openapi.dto.account;

import lombok.Data;

@Data
public class AccountListRequestDto {
	private String user_seq_no; // 사용자일련번호
	private String include_cancel_yn; // 해지계좌포함여부(Y:해지계좌포함, N:해지계좌불포함)
	private String sort_order; // 정렬순서(D:Descending, A:Ascending)
}
