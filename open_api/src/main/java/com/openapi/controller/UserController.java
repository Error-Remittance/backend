package com.openapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.dto.CommonResponse;
import com.openapi.dto.user.UserInfoRequestDto;
import com.openapi.dto.user.UserInfoResponseDto;
import com.openapi.service.UserApiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final CommonResponseMaker commonResponseMaker;
	private final UserApiService userApiService;

	@GetMapping("/user/{user_seq_no}")
	public CommonResponse<UserInfoResponseDto> getUserInfo(@PathVariable String user_seq_no) {
		UserInfoRequestDto userInfoRequestDto = new UserInfoRequestDto();
		userInfoRequestDto.setUser_seq_no(user_seq_no);
		return commonResponseMaker.makeSucceedCommonResponse(userApiService.requestUserInfo(userInfoRequestDto));
	}
}
