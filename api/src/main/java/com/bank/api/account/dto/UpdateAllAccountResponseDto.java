package com.bank.api.account.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.component.account.vo.UpdateAllAccountResponseVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UpdateAllAccountResponseDto {
	private final List<AccountDto> accountList;

	public static UpdateAllAccountResponseDto of(UpdateAllAccountResponseVo vo) {
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
