package com.bank.api.transaction.dto;

import com.bank.component.transaction.vo.MakeReturnRequestRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class MakeReturnRequestRequestDto {
	private final long transactionId;
	private final String message;
	private final String reason;

	public MakeReturnRequestRequestVo toVo() {
		return MakeReturnRequestRequestVo.builder()
			.transactionId(transactionId)
			.message(message)
			.reason(reason)
			.build();
	}
}
