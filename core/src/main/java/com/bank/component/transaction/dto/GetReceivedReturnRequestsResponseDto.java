package com.bank.component.transaction.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.component.account.vo.GetReceivedReturnRequestsResponseVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GetReceivedReturnRequestsResponseDto {
	private final List<ReturnRequestDto> returnRequestList;

	public static GetReceivedReturnRequestsResponseDto of(GetReceivedReturnRequestsResponseVo vo) {
		return builder()
			.returnRequestList(
				vo.getReceivedReturnRequestList()
					.stream()
					.map(returnRequestVo -> ReturnRequestDto.of(returnRequestVo))
					.collect(Collectors.toList())
			)
			.build();
	}
}
