package com.bank.api.transaction.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.component.transaction.vo.GetTransactionListResponseVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GetTransactionListResponseDto {
	private final List<TransactionDto> transactionList;

	public static GetTransactionListResponseDto of(GetTransactionListResponseVo vo) {
		return builder()
			.transactionList(
				vo.getTransactionList()
					.stream()
					.map(transactionVo -> TransactionDto.of(transactionVo))
					.collect(Collectors.toList())
			)
			.build();
	}
}
