package com.openapi.dto.account;

import lombok.Data;

/**
 * 계좌 해지 API
 */
@Data
public class ResignAccountRequestDto {
	private String bank_tran_id;
	private String scope;
	private String fintech_use_num;
}
