package com.bank.component.user.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SignUpRequestVo {
	private final String userId;
	private final String password;
	private final String name;
	private final String phoneNumber;
}
