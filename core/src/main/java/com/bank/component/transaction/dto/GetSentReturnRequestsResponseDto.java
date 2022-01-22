package com.bank.component.transaction.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.component.transaction.vo.GetSentReturnRequestsResponseVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GetSentReturnRequestsResponseDto {
	private final List<ReturnRequestDto> returnRequestList;

	public static GetSentReturnRequestsResponseDto of(GetSentReturnRequestsResponseVo vo) {
		return builder()
			.returnRequestList(
				vo.getSentReturnRequestList()
					.stream()
					.map(returnRequestVo -> ReturnRequestDto.of(returnRequestVo))
					.collect(Collectors.toList())
			)
			.build();
	}
}
