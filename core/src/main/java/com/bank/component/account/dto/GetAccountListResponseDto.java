package com.bank.component.account.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.component.account.vo.GetAccountListResponseVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GetAccountListResponseDto {
	private final List<AccountDto> accountList;

	public static GetAccountListResponseDto of(GetAccountListResponseVo vo) {
		return builder()
			.accountList(
				vo.getAccountList()
					.stream()
					.map(accountVo -> AccountDto.of(accountVo))
					.collect(Collectors.toList())
			)
			.build();
	}
}
