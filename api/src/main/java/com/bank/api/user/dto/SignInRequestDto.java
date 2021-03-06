package com.bank.api.user.dto;

import com.bank.component.user.vo.SignInRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SignInRequestDto {
	private final String userId;
	private final String password;
	private final String fcmToken;

	public SignInRequestVo toVo() {
		return SignInRequestVo.builder()
			.userId(userId)
			.password(password)
			.fcmToken(fcmToken)
			.build();
	}
}
