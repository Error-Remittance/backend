package com.bank.component.user.dto;

import com.bank.component.user.vo.AppUserVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AppUserDto {
	private final Long appUserId;
	private final String userId;
	private final String name;
	private final String phoneNumber;

	public static AppUserDto of(AppUserVo vo) {
		return builder()
			.appUserId(vo.getAppUserId())
			.userId(vo.getUserId())
			.name(vo.getName())
			.phoneNumber(vo.getPhoneNumber())
			.build();
	}
}
