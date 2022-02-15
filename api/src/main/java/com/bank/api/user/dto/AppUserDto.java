package com.bank.api.user.dto;

import com.bank.component.user.domain.User;
import com.bank.component.user.vo.AppUserVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AppUserDto {

	private String username;

	public static AppUserDto of(User user) {
		return builder()
			.username(user.getUsername())
			.build();
	}
}
