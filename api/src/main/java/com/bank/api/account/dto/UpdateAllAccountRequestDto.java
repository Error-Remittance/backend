package com.bank.api.account.dto;

import com.bank.component.account.vo.UpdateAllAccountRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UpdateAllAccountRequestDto {
	private final String userId;
	private final String password;

	public UpdateAllAccountRequestVo toVo() {
		return UpdateAllAccountRequestVo.builder()
			.userId(userId)
			.password(password)
			.build();
	}
}
