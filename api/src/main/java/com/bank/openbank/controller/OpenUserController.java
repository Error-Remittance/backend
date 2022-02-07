package com.bank.openbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.dto.CommonResponse;
import com.bank.openbank.dto.user.UserInfoRequestDto;
import com.bank.openbank.dto.user.UserInfoResponseDto;
import com.bank.openbank.dto.user.UserUnlinkRequestDto;
import com.bank.openbank.dto.user.UserUnlinkResponseDto;
import com.bank.openbank.service.UserApiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OpenUserController {
	private final CommonResponseMaker commonResponseMaker;
	private final UserApiService userApiService;

	/**
	 * 사용자 정보 조회 API
	 * @param user_seq_no
	 * @return
	 */
	@GetMapping("/user/{user_seq_no}")
	public CommonResponse<UserInfoResponseDto> getUserInfo(@PathVariable String user_seq_no) {
		UserInfoRequestDto userInfoRequestDto = new UserInfoRequestDto();
		userInfoRequestDto.setUser_seq_no(user_seq_no);
		return commonResponseMaker.makeSucceedCommonResponse(userApiService.requestUserInfo(userInfoRequestDto));
	}

	/**
	 * 사용자 탈퇴 API
	 */
	@PostMapping("/user/unlink/{user_seq_no}")
	public CommonResponse<UserUnlinkResponseDto> unlinkUser(@PathVariable String user_seq_no) {
		UserUnlinkRequestDto userUnlinkRequestDto = new UserUnlinkRequestDto();
		userUnlinkRequestDto.setUser_seq_no(user_seq_no);
		return commonResponseMaker.makeSucceedCommonResponse(userApiService.unlinkUser(userUnlinkRequestDto));
	}
}
