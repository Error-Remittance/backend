package com.bank.api.user.dto;

import com.bank.component.user.vo.SignUpRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SignUpRequestDto {
	private final String userId;
	private final String password;
	private final String name;
	private final String phoneNumber;

	public SignUpRequestVo toVo() {
		return SignUpRequestVo.builder()
			.userId(userId)
			.password(password)
			.name(name)
			.phoneNumber(phoneNumber)
			.build();
	}
}
