package com.bank.api.user.controller;

import com.bank.api.user.dto.AppUserDto;
import com.bank.api.user.dto.SignInRequestDto;
import com.bank.api.user.dto.SignUpRequestDto;
import com.bank.api.user.service.AppUserService;
import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.dto.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SignController {

	private final AppUserService userService;
	private final CommonResponseMaker commonResponseMaker;

	@PostMapping("/sign/complete")
	public CommonResponse<AppUserDto> completeSignUp(@RequestBody SignUpRequestDto requestDto) throws IOException {

		final AppUserDto responseDto = AppUserDto.of(userService.completeSignUp(requestDto.toVo()));

		return commonResponseMaker.makeSucceedCommonResponse(responseDto);
	}

	@PostMapping("/sign/login")
	public CommonResponse<AppUserDto> signIn(@RequestBody SignInRequestDto requestDto) {

		final AppUserDto responseDto = AppUserDto.of(userService.signIn(requestDto.toVo()));

		return commonResponseMaker.makeSucceedCommonResponse(responseDto);
	}

}
